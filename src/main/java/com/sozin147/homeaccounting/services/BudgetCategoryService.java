package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.BudgetCategory;
import com.sozin147.homeaccounting.model.Category;
import com.sozin147.homeaccounting.model.CustomUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BudgetCategoryService {
  void create(BudgetCategory budgetCategory);
  List<BudgetCategory> getCategories(CustomUser customUser);
  BudgetCategory getCategoryBudget(Category category, CustomUser customUser);
}
