package cs.roosevelt.onlineshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cs.roosevelt.onlineshop.model.Product;
import cs.roosevelt.onlineshop.service.ProductService;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/products")
public class ProductController {

	

    
	@Autowired
    private ProductService productService;

    /**
     * fetch-all-products endpoint
     *
     * NOTE: Used only for debugging or admins; this
     * is not used in the angular project
     * @return
     */
    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Product>> fetchAllProducts(HttpSession session) {
        return productService.getAll(session);
    }

    /**
     * fetch-one-product endpoint
     * @param productId
     * @return
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> fetchProduct(@PathVariable("id") String productId) {
        return productService.getOne(productId);
    }

    /**
     * fetch-all-by-category endpoint
     * @param categoryType
     * @return
     */
    @GetMapping("/search/category/{categoryType}")
    public List<Product> fetchAllProductsByCategory(@PathVariable("categoryType") Integer categoryType) {
        return productService.getAllByCategory(categoryType);
    }

    /**
     * fetch-all-containing-search-string endpoint
     * @param searchStr
     * @return
     */
    @GetMapping("/search/product/{searchStr}")
    public List<Product> fetchAllProductsContainingSearchString(@PathVariable("searchStr") String searchStr) {
        return productService.getAllContainingSearchString(searchStr);
    }

    /**
     * add-product endpoint
     * @param productToAdd
     * @return
     */
    @PostMapping(value = {"/add", "/add/"})
    public ResponseEntity<Optional<Product>> addProduct(@RequestBody Product productToAdd, HttpSession session) {
        return productService.save(productToAdd, session);
    }

    /**
     * edit-product endpoint
     * @param productToUpdate
     * @return
     */
    @PutMapping(value = {"/edit", "/edit/"})
    public ResponseEntity<Optional<Product>> editProduct(@RequestBody Product productToUpdate, HttpSession session) {
        return productService.update(productToUpdate, session);
    }

    /**
     * delete-product endpoint
     * @param productId
     * @return
     */
    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable("id") String productId, HttpSession session) {
        return productService.delete(productId, session);
    }

}
