package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.CategoryDAO;
import com.sozin147.homeaccounting.model.Category;
import com.sozin147.homeaccounting.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIml implements CategoryService {

    @Autowired
    private CategoryDAO categoriesDAO;

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByName(String name) {
        return categoriesDAO.findByName(name);
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        categoriesDAO.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoriesDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(long id) {
        return categoriesDAO.findById(id);
    }
}
