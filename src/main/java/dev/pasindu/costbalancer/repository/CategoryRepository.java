package dev.pasindu.costbalancer.repository;

import dev.pasindu.costbalancer.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    List<Category> findAllCategories();
    Optional<Category> findById(Integer id);
}
