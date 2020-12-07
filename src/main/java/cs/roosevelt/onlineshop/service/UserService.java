package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.dto.LoginForm;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.model.UserSignupOtp;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface UserService {

    List<User> getAll();

    User getOne(String email);

    ResponseEntity<User> register(User user);

    ResponseEntity<User> update(User user);

    ResponseEntity<User> login(LoginForm credentials, HttpSession session);

    ResponseEntity<String> logout(HttpSession session);
    
    UserSignupOtp generateOTP(String emailId);

}
