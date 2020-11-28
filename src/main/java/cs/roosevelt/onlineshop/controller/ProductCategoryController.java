package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.entity.ProductCategory;
import cs.roosevelt.onlineshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public List<ProductCategory> fetchAllCategories() {
        return productCategoryService.getAllCategories();
    }
}
