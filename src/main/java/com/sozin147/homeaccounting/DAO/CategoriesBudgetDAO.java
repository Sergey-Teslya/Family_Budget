package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.CategoriesBudget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesBudgetDAO extends JpaRepository<CategoriesBudget, Long> {
    CategoriesBudget findByName(String name);
}
