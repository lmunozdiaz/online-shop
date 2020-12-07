package cs.roosevelt.onlineshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.roosevelt.onlineshop.model.Product;
import cs.roosevelt.onlineshop.service.ProductService;

@CrossOrigin
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
    public List<Product> fetchAllProducts() {
        return productService.getAll();
    }

    /**
     * fetch-one-product endpoint
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public Optional<Product> fetchProduct(@PathVariable("id") String id) {
        return productService.getOne(id);
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
    
   
}
