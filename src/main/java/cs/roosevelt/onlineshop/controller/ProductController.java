package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.entity.Product;
import cs.roosevelt.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // fetch-all-products endpoint
    @GetMapping(value = {"/", ""})
    public List<Product> fetchAllProducts() {
        return productService.getAllProducts();
    }

    // fetch-all-by-category endpoint
    @GetMapping("/search/category/{id}")
    public List<Product> getAllProductsByCategory(@PathVariable("id") Long id) {
        return productService.getAllProductsByCategory(id);
    }
}
