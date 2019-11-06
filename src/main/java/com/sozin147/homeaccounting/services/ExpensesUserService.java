package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;

import java.util.List;

public interface ExpensesUserService {
    void addExpenses(ExpensesUser expenses);

    List<ExpensesUser> findAllExpensesByUser(CustomUser user);

    List<ExpensesUser> findAllExpensesForDay(CustomUser user, int date);

    List<ExpensesUser> findAllExpensesForLastWeek(CustomUser user, int date);

    List<ExpensesUser> findAllExpensesForLastMonth(CustomUser user, int date);
}
