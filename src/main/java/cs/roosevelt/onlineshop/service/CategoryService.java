package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.model.Category;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface CategoryService {

    ResponseEntity<List<Category>> getAll();

    ResponseEntity<String> add(Category categoryToAdd, HttpSession session);

    ResponseEntity<String> update(Category categoryToUpdate, HttpSession session);

    ResponseEntity<String> delete(Long categoryId, HttpSession session);

}
