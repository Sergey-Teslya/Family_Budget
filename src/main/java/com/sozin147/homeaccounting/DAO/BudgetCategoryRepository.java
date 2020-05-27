package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.BudgetCategory;
import com.sozin147.homeaccounting.model.Category;
import com.sozin147.homeaccounting.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Integer> {
  List<BudgetCategory> findAllByCustomUser(CustomUser customUser);
  BudgetCategory findByCategoryAndCustomUser(Category category, CustomUser customUser);
}
