package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.entity.ProductCategory;
import cs.roosevelt.onlineshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/product-category")
    public List<ProductCategory> findAll() {
        return productCategoryService.findAll();
    }
}
