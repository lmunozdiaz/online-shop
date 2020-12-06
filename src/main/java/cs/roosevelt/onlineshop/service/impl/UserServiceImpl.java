package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.dto.LoginForm;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * UserServiceImpl defines the method signatures provided
 * by the UserService interface for the controller to use.
 *
 * Within each method, it uses methods from
 * the UserRepository.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * The getAll() retrieves all the users from the db.
     * @return All the users.
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * The getOne() retrieves a user by their
     * email (natural id)from the db.
     * @param email
     * @return The user by email.
     */
    @Override
    public User getOne(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * The register() saves a new user to the db.
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
                // yes, the password is valid
                session.setAttribute("user", existingUser);

                return  new ResponseEntity<>(existingUser, HttpStatus.OK);
            } else {
                // no, the password is not valid

                // create a return user to display the invalid credentials
                User invalidUser = new User(credentials.getEmail(), credentials.getPassword());

                return new ResponseEntity<>(invalidUser, HttpStatus.NOT_FOUND);
            }
        } else {
            // no, the user was not found

            // create a return user to display the invalid credentials
            User invalidUser = new User(credentials.getEmail(), credentials.getPassword());

            return new ResponseEntity<>(invalidUser, HttpStatus.NOT_FOUND);
        }

    }

    /**
     * The logout() ends the active http session.
     * @param session
     * @return An http response verifying the session termination.
     */
    @Override
    public ResponseEntity<String> logout(HttpSession session) {

        // end the session
        session.invalidate();

        return new ResponseEntity<>("session ended", HttpStatus.OK);

    }
}
