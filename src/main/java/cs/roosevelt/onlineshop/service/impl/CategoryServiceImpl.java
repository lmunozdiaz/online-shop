package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.entity.Category;
import cs.roosevelt.onlineshop.repository.CategoryRepository;
import cs.roosevelt.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // get all the categories from the db
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
