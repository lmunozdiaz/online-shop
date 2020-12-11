package cs.roosevelt.onlineshop.repository;

import cs.roosevelt.onlineshop.model.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository used by the CategoryServiceImpl
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByCategoryType(Integer categoryTypeToFind);

	List<Category> findByOrderByCategoryTypeAsc();

}
