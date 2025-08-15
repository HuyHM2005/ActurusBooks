package com.example.bookstore.service;

import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllActiveCategories() {
        return categoryRepository.findByIsActiveTrue();
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setIsActive(false);
            categoryRepository.save(category.get());
        }
    }

    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryNameAndIsActiveTrue(categoryName);
    }

    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}