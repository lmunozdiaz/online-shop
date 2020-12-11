package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.Category;
import cs.roosevelt.onlineshop.repository.CategoryRepository;
import cs.roosevelt.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CategoryServiceImpl defines the method signatures provided
 * by the CategoryService interface for the controller to use.
 *
 * Within each method, it uses methods from
 * the CategoryRepository.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * The getAll() retrieves all the categories from the db.
     * @return All the categories.
     */
    @Override
    public List<Category> getAll() {    
        return categoryRepository.findByOrderByCategoryTypeAsc();
    }

    /**
     * The add() saves a new category to the db.
     * @param categoryToAdd
     * @return
     */
    @Override
    public ResponseEntity<Category> add(Category categoryToAdd) {

        // find the category
        Optional<Category> existingCategory = categoryRepository.findById(categoryToAdd.getId());

        // does the category exist?
        if (existingCategory != null) {

            // yes, it does exist; don't add it

            // return a denial response
            return new ResponseEntity<>(null, HttpStatus.FOUND);

        } else {

            //no, it doesn't exist; add it to the db
            return new ResponseEntity<>(categoryRepository.save(categoryToAdd), HttpStatus.OK);

        }

    }

    /**
     * The update() updates an existing category in the db.
     * @param categoryToUpdate
     * @return
     */
    @Override
    public ResponseEntity<Category> update(Category categoryToUpdate) {

        // find the category
        Optional<Category> existingCategory = categoryRepository.findById(categoryToUpdate.getId());

        // does the category exist?
        if (existingCategory != null) {

            // yes, it does exist; update the category
            return new ResponseEntity<>(categoryRepository.save(categoryToUpdate), HttpStatus.OK);

        } else {

            //no, it doesn't exist; return denial response
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }

    }

    /**
     * The delete() deletes an existing category from the db.
     * @param categoryId
     * @return
     */
    @Override
    public ResponseEntity<Category> delete(Long categoryId) {

        // does the category exist?
        if (categoryRepository.existsById(categoryId)) {

            // yes, it does exist; delete it
            categoryRepository.deleteById(categoryId);

            // respond with confirmation
            return new ResponseEntity<>(null, HttpStatus.OK);

        } else {

            // no, it doesn't exist; return a denial response
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }

    }
}
