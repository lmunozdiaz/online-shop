package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository used by the CategoryServiceImpl
 */
public interface CategoryRepository extends JpaRepository<Category, Long> { }
