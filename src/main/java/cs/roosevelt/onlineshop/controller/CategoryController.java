package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.model.Category;
import cs.roosevelt.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
// url suffix for all mappings
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * fetch-all-categories endpoint
     * @return
     */
    @GetMapping(value = {"", "/"})
    public List<Category> fetchAllCategories() { return categoryService.getAll(); }

    /**
     * add-category endpoint
     * @param categoryToAdd
     * @return
     */
    @PostMapping(value = {"/add", "/add/"})
    public ResponseEntity<Category> addCategory(@RequestBody Category categoryToAdd) {
        return categoryService.add(categoryToAdd);
    }

    /**
     * edit-category endpoint
     * @param categoryToUpdate
     * @return
     */
    @PutMapping(value = {"/edit", "/edit/"})
    public ResponseEntity<Category> editCategory(@RequestBody Category categoryToUpdate) {
        return categoryService.add(categoryToUpdate);
    }

    /**
     * delete-category endpoint
     * @param categoryId
     * @return
     */
    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long categoryId) {
        return categoryService.delete(categoryId);
    }

}
