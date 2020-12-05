package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.entity.Product;
import cs.roosevelt.onlineshop.repository.ProductRepository;
import cs.roosevelt.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductServiceImpl defines the method signatures provided
 * by the ProductService interface for the controller to use.
 *
 * Within each method, it uses methods from
 * the ProductRepository.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // get all the products from the db
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // get a product by the given ID
    @Override
    public Optional<Product> getOne(String id) { return productRepository.findById(id); }

    // get all the products by the given category type from the db
    @Override
    public List<Product> getAllByCategory(Integer categoryType) {
        return productRepository.findAllByCategoryType(categoryType);
    }

    // get all the products containing the search string from the db
    @Override
    public List<Product> getAllContainingSearchString(String searchStr) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchStr, searchStr);
    }

}
