package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryByName(String name);

    Optional<Category> getCategoryById(long id);
}
