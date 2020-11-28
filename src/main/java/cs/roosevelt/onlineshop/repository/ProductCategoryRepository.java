package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.entity.Product;
import cs.roosevelt.onlineshop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository used by the ProductCategoryServiceImpl
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> { }
