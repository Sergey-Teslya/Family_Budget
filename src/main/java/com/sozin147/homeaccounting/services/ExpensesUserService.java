package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;

import java.util.List;

public interface ExpensesUserService {
    void addExpenses(ExpensesUser expenses);

    long getSumExpensesUserForLastMonth(CustomUser user);

    List<ExpensesUser> findAllExpensesByUser(CustomUser user);

    List<ExpensesUser> findAllExpensesForDate(CustomUser user, int date);

}
