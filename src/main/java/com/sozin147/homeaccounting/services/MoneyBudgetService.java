package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.MoneyBudget;

public interface MoneyBudgetService {
    void addMoneyBudget(MoneyBudget money);

    void updateMoneyBudget(MoneyBudget money);

    MoneyBudget getMoneyBudgetUser(CustomUser user);

    Integer getMoney(CustomUser user);
}
