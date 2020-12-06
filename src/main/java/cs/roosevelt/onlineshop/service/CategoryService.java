package cs.roosevelt.onlineshop.service;

import cs.roosevelt.onlineshop.model.Category;

import java.util.List;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface CategoryService {

    List<Category> getAll();

}
