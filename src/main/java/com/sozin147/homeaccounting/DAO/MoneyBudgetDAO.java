package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.MoneyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoneyBudgetDAO extends JpaRepository<MoneyBudget, Long> {

    @Query("SELECT budget FROM MoneyBudget budget WHERE budget.user = :user")
    MoneyBudget getMoneyBudgetByUser(@Param("user") CustomUser user);

    @Query("SELECT money FROM MoneyBudget WHERE user = :user")
    Integer getMoneyInBudget(@Param("user") CustomUser user);
}
