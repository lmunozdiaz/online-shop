package cs.roosevelt.onlineshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import cs.roosevelt.onlineshop.dto.LoginForm;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/users")
public class UserController {
	
	

    @Autowired
    private UserService userService;
  
    /**
     * fetch-all-users endpoint
     *
     * NOTE: Used only for debugging or admins; this
     * is not used in the angular project
     * @return
     */
    @GetMapping(value = {"", "/"})
    public List<User> fetchAllUsers() {
        return userService.getAll();
    }

    /**
     * fetch-one-user endpoint
     * @param email
     * @return
     */
    @GetMapping(value = {"/{email}", "/{email}/"})
    public User fetchUser(@PathVariable("email") String email) {
        return userService.getOne(email);
    }

    /**
     * register-user endpoint
     * @param user
     * @return
     */
    @PostMapping(value = {"/register", "/register/"})
    public ResponseEntity<User> registerUser(@RequestBody User user) { return userService.register(user); }

    /**
     * update-user endpoint
     * @param user
     * @return
     */
    @PutMapping(value = {"/user/update", "/user/update/"})
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * login-existing-user endpoint
     * @param credentials
     * @param session
     * @return An http status response and user details to verify/deny login attempt.
     */
    @PostMapping(value = {"/user/login", "/user/login/"})
    public ResponseEntity<User> login(@RequestBody final LoginForm credentials, HttpSession session) {
        return userService.login(credentials, session);
    }

    /**
     * logout-existing-session endpoint
     * @param session
     * @return An http status response to verify session termination.
     */
    @GetMapping(value = {"/user/logout", "/user/logout/"})
    public ResponseEntity<String> logout(HttpSession session) {
        return userService.logout(session);
    }

    /**
     * is-logged-in verification endpoint
     * @param session
     * @return
     */
    @GetMapping(value = {"user/loggedIn"})
    public boolean isLoggedIn(HttpSession session) {
        return (session != null) && (session.getAttribute("user") != null);
    }

    /**
     * login-test endpoint; only used for debugging
     * @param request
     * @return A message log.
     */
    @GetMapping(value = {"/user/home", "/user/home/"})
    public String testHome(HttpServletRequest request) {

        // the message to return
        String message = "not logged in";

        // is there an active session with a user?
        if (request.getSession() != null && request.getSession().getAttribute("user") != null) {
            // yes, there is; modify the message to say so
            message = request.getSession().getAttribute("user").toString();
        }

        return message;
    }
    
    @GetMapping("/getOnetimeCode/{emailId}") 
    public int generateOnetimeCode(@PathVariable("emailId") String emailId) {
        
    	return userService.generateOTP(emailId).getOtp();
    	
    }


}
