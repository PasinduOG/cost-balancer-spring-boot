package dev.pasindu.costbalancer.service.impl;

import dev.pasindu.costbalancer.dto.CategoryDto;
import dev.pasindu.costbalancer.entity.Category;
import dev.pasindu.costbalancer.exception.ResourceNotFoundException;
import dev.pasindu.costbalancer.repository.CategoryRepository;
import dev.pasindu.costbalancer.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public boolean addCategory(CategoryDto categoryDto) {
        Category c = new Category();
        c.setName(categoryDto.getName());
        c.setCategories(categoryDto.getCategories());
        return repository.addCategory(c);
    }

    @Override
    public boolean updateCategory(CategoryDto categoryDto, Integer id) {
        getCategoryById(id);
        Category c = new Category();
        c.setId(id);
        c.setName(categoryDto.getName());
        c.setCategories(categoryDto.getCategories());
        return repository.updateCategory(c);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAllCategories();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );
    }
}
