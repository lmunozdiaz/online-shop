package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.entity.User;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getOne(String email) {
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
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

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
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        // was the user found?
        if (existingUser != null) {
            // yes, the user was found; update the user
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            // no, the user wasn't found
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }

    }
}
