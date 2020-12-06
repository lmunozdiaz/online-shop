package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.entity.User;
import cs.roosevelt.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // fetch-all-users endpoint
    // NOTE: Used only for debugging or admins; this
    // is not used in the angular project
    @GetMapping(value = {"", "/"})
    public List<User> fetchAllUsers() {
        return userService.getAll();
    }

    // fetch-one-user endpoint
    @GetMapping(value = {"/{email}", "/{email}/"})
    public Optional<User> fetchUser(@PathVariable("email") String email) {
        return userService.getOne(email);
    }

    // register-user endpoint
    @PostMapping(value = {"/register", "/register/"})
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    // update-user endpoint
    @PostMapping(value = {"/user/update", "/user/update/"})
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.update(user);
    }

}
