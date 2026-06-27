package dev.pasindu.costbalancer.controller;

import dev.pasindu.costbalancer.dto.CategoryDto;
import dev.pasindu.costbalancer.entity.Category;
import dev.pasindu.costbalancer.service.CategoryService;
import io.github.og4dev.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getCategoriesByType(@RequestParam(required = false) String type) {
        List<Category> allCategories = service.getAllCategories();
        if (type != null && !type.isEmpty()) {
            allCategories = allCategories.stream()
                    .filter(c -> c.getCategories().name().equalsIgnoreCase(type))
                    .toList();
        }
        return ApiResponse.success("Categories fetched successfully", allCategories);
    }

    @PostMapping("/add")
    ResponseEntity<ApiResponse<Boolean>> addCategory(@RequestBody CategoryDto categoryDto) {
        boolean saved = service.addCategory(categoryDto);
        return ApiResponse.success("Category added successfully", saved);
    }
}
