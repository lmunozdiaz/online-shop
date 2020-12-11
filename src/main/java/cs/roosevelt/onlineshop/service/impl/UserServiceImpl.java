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
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * <p>
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
    private PasswordEncoder passwordEncoder;


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
    public ResponseEntity<List<User>> getAll(HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "ROLE_MANAGER") {

                    // yes, the user's an admin; get all users
                    return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The getOne() retrieves a user by their
     * email (natural id)from the db.
     *
     * @param email
     * @return The user by email.
     */
    @Override
    public ResponseEntity<User> getOne(String email, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "ROLE_MANAGER") {

                    // yes, the user's an admin; get all users
                    return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The register() saves a new user to the db.
     *
     * @param user
     * @return The registered user and an http response.
     */
    @Override
    public ResponseEntity<User> register(User user, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "ROLE_MANAGER") {

                    // yes, the user's an admin; update the user
                    return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The update() allows admins to update any user in
     * the db, while only letting registered users and employees
     * update their self.
     *
     * @param userToUpdate
     * @return The updated user and an http response.
     */
    @Override
    public ResponseEntity<User> update(User userToUpdate, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, they're valid

                // is the session user an admin?
                if (sessionUser.getRole() == "ROLE_MANAGER") {

                    // yes, they're an admin; proceed with request

                    // does the user-to-update exist?
                    if (userRepository.existsById(userToUpdate.getId())) {

                        // yes, the user exists
                        return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.OK);

                    } else {

                        // no, the user-to-update doesn't exist
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                    // is the session user registered or an employee?
                } else if (sessionUser.getRole() == "ROLE_CUSTOMER" || sessionUser.getRole() == "ROLE_EMPLOYEE") {

                    // yes, they are; they can only update their own profile

                    // does the user-to-update exist?
                    if (userRepository.existsById(userToUpdate.getId())) {

                        // yes, the user exists

                        // are they updating their self?
                        if (sessionUser.equals(userToUpdate)) {

                            // yes, they are; proceed with request
                            return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.OK);

                        } else {

                            // no, they are not; deny the request
                            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                        }

                    } else {

                        // no, the user-to-update doesn't exist
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, they're neither registered nor an employee; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The delete() deletes a user in the db.
     *
     * @param email
     * @return A http status response verifying or denying the deletion.
     */
    @Override
    public ResponseEntity<User> delete(String email, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "ROLE_MANAGER") {

                    // does the user with given email exist?
                    if (userRepository.findByEmail(email) != null) {

                        // yes, the user exists; delete the user
                        return new ResponseEntity<>(userRepository.deleteByEmail(email), HttpStatus.OK);

                    } else {

                        // no, the user doesn't exist; return Not_Found status
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The login() attempts to find an existing user in the db
     * using the passed credentials to start an http session.
     * <p>
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
        User existingUser = userRepository.findByEmail(credentials.getEmail());

        // was the user found?
        if (existingUser != null) {

            // yes, the user was found; is the password valid?
            if (existingUser.getPassword().equals(credentials.getPassword())) {

                // yes, the password is valid; set the session's user
                session.setAttribute("user", existingUser);

                return new ResponseEntity<>(existingUser, HttpStatus.OK);

            } else {

                // no, the password is not valid

                // create a return-user to display the invalid credentials
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
        msg.setText("Activate user by clicking the link http://localhost:8080/api/users/activateUser/" + String.valueOf(otp));
        System.out.println("Email Sent");
        javaMailSender.send(msg);
        return userOtp;
    }

	@Override
	@Transactional
	public String createUser(User user) {
		User user1 = userRepository.findByEmail(user.getEmail());
		if(user1== null) {
			user.setUserActive("N");		
			Random rnd = new Random();
			long n = 10000000 + rnd.nextInt(90000000);
			user.setId(n);
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole("ROLE_CUSTOMER");
			user = userRepository.save(user);
			generateOTP(user.getEmail());
			return "User Registered Successfully";
		}else if("Y".equals(user1.getUserActive()))
			return "User with Email:" + user.getEmail() + " Already  Exists";
		else
			return "User is already Registered but not active.Please check activation Email";
		
	}

	@Override
	public String activateUser(int otp) {
		 UserSignupOtp userOtp = userSignupOtpRepository.findByOtp(otp);
		 if(userOtp != null) {
			 User user = userRepository.findByEmail(userOtp.getEmailId());
			 if(user == null) {
				 return "Invalid Activation URL"; 
			 }else if("Y".equals(user.getUserActive())) {
				 return "User is already Active, Start Shopping"; 
			 }else{
				 user.setUserActive("Y");
				 userRepository.save(user);
				 return "User Registration Successfully Completed, Start Shopping"; 
			 } 
		 }else {
			 return "Invalid Activation URL"; 
		 }
		
	}	
}
