package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.model.Category;
import cs.roosevelt.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
// url suffix for all mappings
@RequestMapping("/products")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * fetch-all-categories endpoint
     * @return
     */
    @GetMapping("/categories")
    public List<Category> fetchAllCategories() {
        return categoryService.getAll();
    }

}
