package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.CategoriesBudgetDAO;
import com.sozin147.homeaccounting.model.CategoriesBudget;
import com.sozin147.homeaccounting.services.CategoriesBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesBudgetServiceIml implements CategoriesBudgetService {

    @Autowired
    private CategoriesBudgetDAO categoriesDAO;

    @Override
    @Transactional(readOnly = true)
    public CategoriesBudget getCategoryByName(String name) {
        return categoriesDAO.findByName(name);
    }

    @Override
    @Transactional
    public void addCategory(CategoriesBudget category) {
        categoriesDAO.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriesBudget> getAll() {
        return categoriesDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoriesBudget> getCategoryById(long id) {
        return categoriesDAO.findById(id);
    }
}
