package cs.roosevelt.onlineshop.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import cs.roosevelt.onlineshop.model.Product;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface ProductService {

    ResponseEntity<List<Product>> getAll(HttpSession session);

    ResponseEntity<Optional<Product>> getOne(String productId);

    List<Product> getAllByCategory(Integer categoryType);

    List<Product> getAllContainingSearchString(String searchStr);

    public ResponseEntity<String>  save(Product productToSave, HttpSession session);

    ResponseEntity<Optional<Product>> update(Product productToUpdate, HttpSession session);

    ResponseEntity<Optional<Product>> delete(String productId, HttpSession session);

}
