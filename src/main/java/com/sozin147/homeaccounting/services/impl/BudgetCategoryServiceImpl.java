package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.BudgetCategoryRepository;
import com.sozin147.homeaccounting.model.BudgetCategory;
import com.sozin147.homeaccounting.model.Category;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.services.BudgetCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetCategoryServiceImpl implements BudgetCategoryService {
  private final BudgetCategoryRepository budgetCategoryRepository;

  public BudgetCategoryServiceImpl(BudgetCategoryRepository budgetCategoryRepository) {
    this.budgetCategoryRepository = budgetCategoryRepository;
  }

  @Override
  public void create(BudgetCategory budgetCategory) {
    budgetCategoryRepository.save(budgetCategory);
  }

  @Override
  public List<BudgetCategory> getCategories(CustomUser customUser) {
    return budgetCategoryRepository.findAllByCustomUser(customUser);
  }

  @Override
  public BudgetCategory getCategoryBudget(Category category, CustomUser customUser) {
    return budgetCategoryRepository.findByCategoryAndCustomUser(category, customUser);
  }
}
