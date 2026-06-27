package dev.pasindu.costbalancer.repository.impl;

import dev.pasindu.costbalancer.entity.Category;
import dev.pasindu.costbalancer.repository.CategoryRepository;
import dev.pasindu.costbalancer.util.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean addCategory(Category category) {
        return jdbcTemplate.update("INSERT INTO categories (name, type) VALUES (?,?)",
                category.getName(),
                category.getCategories().name()) > 0;
    }

    @Override
    public boolean updateCategory(Category category) {
        return jdbcTemplate.update("UPDATE categories SET name = ?, type = ? WHERE id = ?",
                category.getName(),
                category.getCategories().name(),
                category.getId()) > 0;
    }

    @Override
    public List<Category> findAllCategories() {
        return jdbcTemplate.query("SELECT id, name, type FROM categories", (rs, rowNum) -> {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setCategories(Categories.valueOf(rs.getString("type")));
            return c;
        });
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT id, name, type FROM categories WHERE id = ?", (rs, rowNum) -> {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setCategories(Categories.valueOf(rs.getString("type")));
            return c;
        }, id));
    }
}
