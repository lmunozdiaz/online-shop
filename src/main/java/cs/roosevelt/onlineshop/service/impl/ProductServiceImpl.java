package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.Product;
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

    /**
     * The getAll() retrieves all the products from the db.
     * @return All the products.
     */
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * The getOne() retrieves a product by the
     * given ID from the db.
     * @param id
     * @return The user by ID.
     */
    @Override
    public Optional<Product> getOne(String id) { return productRepository.findById(id); }

    /**
     * The getAllByCategory() retrieves all the products by
     * the given category type from the db.
     * @param categoryType
     * @return All the products by category.
     */
    @Override
    public List<Product> getAllByCategory(Integer categoryType) {
        return productRepository.findAllByCategoryType(categoryType);
    }

    /**
     * The getAllContainingSearchString() retrieves all the
     * products containing the search string from the db.
     * @param searchStr
     * @return All the products containing the search string.
     */
    @Override
    public List<Product> getAllContainingSearchString(String searchStr) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchStr, searchStr);
    }

}
