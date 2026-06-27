package dev.pasindu.costbalancer.service;

import dev.pasindu.costbalancer.dto.CategoryDto;
import dev.pasindu.costbalancer.entity.Category;

import java.util.List;

public interface CategoryService {
    boolean addCategory(CategoryDto categoryDto);
    boolean updateCategory(CategoryDto categoryDto, Integer id);
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
}
