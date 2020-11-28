package cs.roosevelt.onlineshop.service.impl;

import cs.roosevelt.onlineshop.entity.ProductCategory;
import cs.roosevelt.onlineshop.repository.ProductCategoryRepository;
import cs.roosevelt.onlineshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductCategoryServiceImpl defines the API, provided by
 * the ProductCategoryService interface, for the controller to use.
 *
 * Within each method, it uses methods from
 * the ProductCategoryRepository.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // retrieve all the products from the db
    @Override
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }
}
