package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.model.Product;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface ProductService {

    List<Product> getAll();

    Optional<Product> getOne(String id);

    List<Product> getAllByCategory(Integer categoryType);

    List<Product> getAllContainingSearchString(String searchStr);

    ResponseEntity<Optional<Product>> save(Product productToSave);

    ResponseEntity<Optional<Product>> update(Product productToUpdate);

    ResponseEntity<Optional<Product>> delete(String id);

}
