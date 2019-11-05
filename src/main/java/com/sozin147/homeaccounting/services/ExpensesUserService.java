package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;

import java.util.List;

public interface ExpensesUserService {
    void addExpenses(ExpensesUser expenses);

    List<ExpensesUser> findAllExpensesByUser(CustomUser user);

    List<ExpensesUser> findAllExpensesForLastWeek(CustomUser user);

    List<ExpensesUser> findAllExpensesForLastMonth(CustomUser user);
}
