package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.entity.Product;

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

}
