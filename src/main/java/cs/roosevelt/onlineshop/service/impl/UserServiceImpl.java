package cs.roosevelt.onlineshop.service.impl;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cs.roosevelt.onlineshop.dto.LoginForm;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.model.UserSignupOtp;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.repository.UserSignupOtpRepository;
import cs.roosevelt.onlineshop.service.UserService;

/**
 * UserServiceImpl defines the method signatures provided
 * by the UserService interface for the controller to use.
 *
 * Within each method, it uses methods from
 * the UserRepository.
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${OnetimeCodeLength}")
    private int len;

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSignupOtpRepository userSignupOtpRepository;

    /**
     * The getAll() retrieves all the users from the db.
     *
     * @return All the users.
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * The getOne() retrieves a user by their
     * email (natural id)from the db.
     *
     * @param email
     * @return The user by email.
     */
    @Override
    public User getOne(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * The register() saves a new user to the db.
     *
     * @param user
     * @return The registered user and an http response.
     */
    @Override
    public ResponseEntity<User> register(User user) {

        // find the user
        User existingUser = userRepository.findByEmail(user.getEmail());

        // was the user found?
        if (existingUser == null) {
            // no user found; save the user to the db
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } else {
            // user was found
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }

    }

    /**
     * The update() updates an existing user in the db.
     *
     * @param user
     * @return The updated user and an http response.
     */
    @Override
    public ResponseEntity<User> update(User user) {

        // find the user
        User existingUser = userRepository.findByEmail(user.getEmail());

        // was the user found?
        if (existingUser != null) {

            // yes, the user was found; update the user
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

        } else {

            // no, the user wasn't found
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

        }

    }

    /**
     * The login() attempts to find an existing user in the db
     * using the passed credentials to start an http session.
     *
     * An http session is started using the found user data;
     * it returns a negative http response if the user wasn't
     * found based on the credentials.
     *
     * @param credentials
     * @param session
     * @return An http status response verifying or denying the start of an http session.
     */
    @Override
    public ResponseEntity<User> login(LoginForm credentials, HttpSession session) {

        // find the user
        User existingUser = getOne(credentials.getEmail());

        // was the user found?
        if (existingUser != null) {

            // yes, the user was found; is the password valid?
            if (existingUser.getPassword().equals(credentials.getPassword())) {
                // yes, the password is valid; set the session's user
                session.setAttribute("user", existingUser);

                return new ResponseEntity<>(existingUser, HttpStatus.OK);

            } else {

                // no, the password is not valid

                // create a return user to display the invalid credentials
                User invalidUser = new User(credentials.getEmail(), credentials.getPassword() + ": not valid");

                return new ResponseEntity<>(invalidUser, HttpStatus.NOT_FOUND);

            }
        } else {

            // no, the user was not found

            // create a return user to display the invalid credentials
            User invalidUser = new User(credentials.getEmail() + ": not valid",
                                        credentials.getPassword() + ": not valid");

            return new ResponseEntity<>(invalidUser, HttpStatus.NOT_FOUND);

        }

    }

    /**
     * The logout() ends the active http session.
     *
     * @param session
     * @return An http response verifying the session termination.
     */
    @Override
    public ResponseEntity<String> logout(HttpSession session) {

        // end the session
        session.invalidate();

        return new ResponseEntity<>("session ended", HttpStatus.OK);

    }

    @Override
    @Transactional
    public UserSignupOtp generateOTP(String email) {
        String numbers = "0123456789";
        // Numbers range

        // Randomizer
        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {

            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }

        UserSignupOtp userOtp = new UserSignupOtp(email, Integer.parseInt(String.valueOf(otp)));
        userOtp = userSignupOtpRepository.save(userOtp);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Welcome to Roosevelt Shop");
        msg.setText("Activate user by clicking the link http://localhost:8080/api/users/activateUser/"+String.valueOf(otp));
        System.out.println("Email Sent");
        javaMailSender.send(msg);
        return userOtp;
    }

	@Override
	@Transactional
	public String createUser(User user) {
		if(userRepository.findByEmail(user.getEmail())== null) {
			user.setActive(false);		
			Random rnd = new Random();
			long n = 100000 + rnd.nextInt(900000);
			user.setId(n);
	        //user.setPassword(passwordEncoder.encode(user.getPassword()));
			user = userRepository.save(user);
			generateOTP(user.getEmail());
			return "User Registered Successfully";
		}
		return "User Already Exists Please user a new email id";
		
	}

	@Override
	public String activateUser(int otp) {
		 UserSignupOtp userOtp = userSignupOtpRepository.findByOtp(otp);
		 User user = userRepository.findByEmail(userOtp.getEmailId());
		 if(user == null) {
			 return "Invalid Activation URL"; 
		 }else if(user.isActive()) {
			 return "User is already Active, Start Shopping"; 
		 }else{
			 user.setActive(true);
			 userRepository.save(user);
			 return "User Registration Successfully Completed, Start Shopping"; 
		 }
	}
}
