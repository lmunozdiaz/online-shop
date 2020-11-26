package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
