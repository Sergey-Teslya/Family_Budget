package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.MoneyBudget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyBudgetDAO extends JpaRepository<MoneyBudget, Long> {
}
