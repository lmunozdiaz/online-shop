package cs.roosevelt.onlineshop.controller;

import cs.roosevelt.onlineshop.model.Category;
import cs.roosevelt.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public ResponseEntity<List<Category>> fetchAllCategories() { return categoryService.getAll(); }

    /**
     * add-category endpoint
     * @param categoryToAdd
     * @return
     */
    @PostMapping(value = {"/add", "/add/"})
    public ResponseEntity<String> addCategory(@RequestBody Category categoryToAdd, HttpSession session) {
        return categoryService.add(categoryToAdd, session);
    }

    /**
     * edit-category endpoint
     * @param categoryToUpdate
     * @return
     */
    @PutMapping(value = {"/edit", "/edit/"})
    public ResponseEntity<String> editCategory(@RequestBody Category categoryToUpdate, HttpSession session) {
        return categoryService.add(categoryToUpdate, session);
    }

    /**
     * delete-category endpoint
     * @param categoryId
     * @return
     */
    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId, HttpSession session) {
        return categoryService.delete(categoryId, session);
    }

}
