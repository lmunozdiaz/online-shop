package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.Product;
import cs.roosevelt.onlineshop.repository.ProductRepository;
import cs.roosevelt.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    	if(0==categoryType)
    		return productRepository.findAll();
    	else
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

    @Override
    public ResponseEntity<Optional<Product>> save(Product productToSave) {

        Optional<Product> existingProduct = productRepository.findById(productToSave.getId());

        // does the product exist already?
        if (existingProduct != null) {

            // no, it doesn't exist; save the new product
            return new ResponseEntity(productRepository.save(productToSave), HttpStatus.OK);

        } else {

            // yes, it exists; return null and found status
            return new ResponseEntity<>(null, HttpStatus.FOUND);

        }

    }

    @Override
    public ResponseEntity<Optional<Product>> update(Product productToUpdate) {

        Optional<Product> existingProduct = productRepository.findById(productToUpdate.getId());

        // does the product exist already?
        if (existingProduct != null) {

            // yes, it does exist; update the new product
            return new ResponseEntity(productRepository.save(productToUpdate), HttpStatus.OK);

        } else {

            // no, it doesn't exists; return null and not found status
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Optional<Product>> delete(String id) {

        Optional<Product> existingProduct = productRepository.findById(id);

        // does the product exist?
        if (existingProduct != null) {

            // yes, it does exist; delete product
            productRepository.delete(existingProduct.get());

            // mold the return variable for readability
            Optional<Product> deletedProduct = existingProduct;

            return new ResponseEntity(deletedProduct, HttpStatus.OK);

        } else {

            // no, it doesn't exists; return null and not found status
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }

    }
}
