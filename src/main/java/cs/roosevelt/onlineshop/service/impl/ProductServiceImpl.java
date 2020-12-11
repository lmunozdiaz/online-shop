package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.Product;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.repository.ProductRepository;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * ProductServiceImpl defines the method signatures provided
 * by the ProductService interface for the controller to use.
 * <p>
 * Within each method, it uses methods from
 * the ProductRepository.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * The getAll() retrieves all the products from the db.
     *
     * @return All the products.
     */
    @Override
    public ResponseEntity<List<Product>> getAll(HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "ROLE_MANAGER") {

                    // yes, the user's an admin; get all users
                    return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The getOne() retrieves a product by the
     * given ID from the db.
     *
     * @param id
     * @return The user by ID.
     */
    @Override
    public ResponseEntity<Optional<Product>> getOne(String id) {

        return null;

    }

    /**
     * The getAllByCategory() retrieves all the products by
     * the given category type from the db.
     *
     * @param categoryType
     * @return All the products by category.
     */
    @Override
    public List<Product> getAllByCategory(Integer categoryType) {
        if (0 == categoryType)
            return productRepository.findAll();
        else
            return productRepository.findAllByCategoryType(categoryType);
    }

    /**
     * The getAllContainingSearchString() retrieves all the
     * products containing the search string from the db.
     *
     * @param searchStr
     * @return All the products containing the search string.
     */
    @Override
    public List<Product> getAllContainingSearchString(String searchStr) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchStr, searchStr);
    }

    @Override
    public ResponseEntity<String> save(Product productToSave, HttpSession session) {

    	Product existingProduct = productRepository.findByName(productToSave.getName().trim());

        // does the product exist already?
        if (existingProduct == null) {
        	Random rnd = new Random();
			long n = 10000000 + rnd.nextInt(90000000);
        	productToSave.setId(String.valueOf(n));
        	productToSave.setCreateTime(new Date());
        	productToSave.setUpdateTime(new Date());
        	productToSave.setStatus(0);
            // no, it doesn't exist; save the new product
            return new ResponseEntity(productRepository.save(productToSave), HttpStatus.OK);

        } else {

            // yes, it exists; return null and found status
            return new ResponseEntity<>("Product Already Exists", HttpStatus.FOUND);

        }

    }

    @Override
    public ResponseEntity<Optional<Product>> update(Product productToUpdate, HttpSession session) {

        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "Manager") {

                    // yes, the user's an admin; proceed with the request

                    // try to get the product
                    Optional<Product> existingProduct = productRepository.findById(productToUpdate.getId());

                    // does the product exist already?
                    if (existingProduct != null) {

                        // yes, it does exist; update the new product
                        return new ResponseEntity(productRepository.save(productToUpdate), HttpStatus.OK);

                    } else {

                        // no, it doesn't exists; return null and not found status
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }
        // is there an active session?

    }

    @Override
    public ResponseEntity<Optional<Product>> delete(String productId, HttpSession session) {

        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole() == "Manager") {

                    // yes, the user's an admin; proceed with the request

                    // does the product exist?
                    if (productRepository.existsById(productId)) {

                        // yes, it does exist; delete the new product
                        productRepository.deleteById(productId);

                        // return an OK status
                        return new ResponseEntity(null, HttpStatus.OK);

                    } else {

                        // no, it doesn't exists; return null and not found status
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        }

    }
}
