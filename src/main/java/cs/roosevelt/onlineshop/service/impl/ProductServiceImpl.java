package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.entity.Product;
import cs.roosevelt.onlineshop.repository.ProductRepository;
import cs.roosevelt.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementer class for ProductService.
 * It must implement its method signatures that will
 * be used by the controller.
 *
 * Aids organization and readability.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    // get all the products from the db
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // get all the products by given category from the db
    @Override
    public List<Product> getAllProductsByCategory(Long id) { return productRepository.findAllByCategoryId(id); }
}
