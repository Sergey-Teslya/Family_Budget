package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.CategoriesBudget;

import java.util.List;
import java.util.Optional;

public interface CategoriesBudgetService {
    void addCategory(CategoriesBudget category);

    List<CategoriesBudget> getAllCategories();

    CategoriesBudget getCategoryByName(String name);

    Optional<CategoriesBudget> getCategoryById(long id);
}
