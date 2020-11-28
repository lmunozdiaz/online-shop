package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.entity.Product;

import java.util.List;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(Long id);

    List<Product> getAllProductsByKeyword(String keyword);

}
