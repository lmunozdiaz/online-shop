package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface UserService {

    List<User> getAll();

    Optional<User> getOne(String email);

    ResponseEntity<User> register(User user);

    ResponseEntity<User> update(User user);

}
