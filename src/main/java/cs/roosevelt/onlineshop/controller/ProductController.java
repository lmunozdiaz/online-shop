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
    public List<Product> fetchAllProductsByCategory(@PathVariable("id") Long id) {
        return productService.getAllProductsByCategory(id);
    }

    // fetch-all-by-keyword endpoint
    @GetMapping("/search/product/{keyword}")
    public List<Product> fetchAllProductsByKeyword(@PathVariable("keyword") String keyword) {
        return productService.getAllProductsByKeyword(keyword);
    }
}
