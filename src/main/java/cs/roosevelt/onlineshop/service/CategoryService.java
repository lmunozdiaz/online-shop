package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface CategoryService {

    List<Category> getAll();

    ResponseEntity<Category> add(Category categoryToAdd);

    ResponseEntity<Category> update(Category categoryToUpdate);

    ResponseEntity<Category> delete(Long categoryId);

}
