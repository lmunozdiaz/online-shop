package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.model.Category;
import cs.roosevelt.onlineshop.model.User;
import cs.roosevelt.onlineshop.repository.CategoryRepository;
import cs.roosevelt.onlineshop.repository.UserRepository;
import cs.roosevelt.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private UserRepository userRepository;

    /**
     * The getAll() retrieves all the categories from the db.
     * @return All the categories.
     */
    @Override
    public ResponseEntity<List<Category>> getAll() {

        // return all the categories
        return new ResponseEntity<>(categoryRepository.findByOrderByCategoryTypeAsc(), HttpStatus.OK);

    }

    /**
     * The add() saves a new category to the db.
     * @param categoryToAdd
     * @return
     */
    @Override
    public ResponseEntity<String> add(Category categoryToAdd, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, the user's an admin

                    // find the category
                    Optional<Category> existingCategory = categoryRepository.findById(categoryToAdd.getId());

                    // does the category exist?
                    if (existingCategory.isPresent()) {

                        // yes, the category exists; return a denial response
                        return new ResponseEntity<>("Category already exists", HttpStatus.FOUND);

                    } else {

                        // no, the category doesn't exist; add the category and return OK status

                        categoryRepository.save(categoryToAdd);

                        return new ResponseEntity<>("Category added", HttpStatus.OK);

                    }

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>("User is unauthorized", HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>("Current user not valid", HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>("No active session", HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The update() updates an existing category in the db.
     * @param categoryToUpdate
     * @return
     */
    @Override
    public ResponseEntity<String> update(Category categoryToUpdate, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, the user's an admin

                    // find the category
                    Optional<Category> existingCategory = categoryRepository.findById(categoryToUpdate.getId());

                    // does the category exist?
                    if (existingCategory.isPresent()) {

                        // yes, the category exists; update the category and return OK status

                        categoryRepository.save(categoryToUpdate);

                        return new ResponseEntity<>("Category updated", HttpStatus.OK);

                    } else {

                        // no, the category doesn't exist; return denial response
                        return new ResponseEntity<>("Category does not exist", HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>("User is unauthorized", HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>("Current user not valid", HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>("No active session", HttpStatus.UNAUTHORIZED);

        }

    }

    /**
     * The delete() deletes an existing category from the db.
     * @param categoryId
     * @return
     */
    @Override
    public ResponseEntity<String> delete(Long categoryId, HttpSession session) {

        // is there an active session?
        if (session != null && session.getAttribute("user") != null) {

            // get the user from the session
            User sessionUser = (User) session.getAttribute("user");

            // is the session user valid?
            if (sessionUser != null && userRepository.existsById(sessionUser.getId())) {

                // yes, the user is valid

                // is the valid user an admin?
                if (sessionUser.getRole().equals("ROLE_MANAGER")) {

                    // yes, the user's an admin

                    // does the category exist?
                    if (categoryRepository.existsById(categoryId)) {

                        // yes, it does exist; delete it
                        categoryRepository.deleteById(categoryId);

                        // respond with confirmation
                        return new ResponseEntity<>("Category deleted", HttpStatus.OK);

                    } else {

                        // no, it doesn't exist; return a denial response
                        return new ResponseEntity<>("Category does not exist", HttpStatus.NOT_FOUND);

                    }

                } else {

                    // no, the user is not an admin; deny the request
                    return new ResponseEntity<>("User is unauthorized", HttpStatus.UNAUTHORIZED);

                }

            } else {

                // no valid user found
                return new ResponseEntity<>("Current user not valid", HttpStatus.NOT_FOUND);

            }

        } else {

            // no, there's no active session; return denial response
            return new ResponseEntity<>("No active session", HttpStatus.UNAUTHORIZED);

        }

    }
}
