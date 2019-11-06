package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.ExpensesUserDAO;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;
import com.sozin147.homeaccounting.model.QuantityDay;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpensesUserServiceImp implements ExpensesUserService {

    @Autowired
    private ExpensesUserDAO expensesDAO;

    @Override
    @Transactional
    public void addExpenses(ExpensesUser expenses) {
        expensesDAO.save(expenses);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpensesUser> findAllExpensesByUser(CustomUser user) {
        return expensesDAO.getExpensesByUserLogin(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpensesUser> findAllExpensesForDay(CustomUser user, int date) {
        return expensesDAO.findAllExpensesUserInTheTime(user, date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpensesUser> findAllExpensesForLastWeek(CustomUser user, int date) {
        return expensesDAO.findAllExpensesUserInTheTime(user, date);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpensesUser> findAllExpensesForLastMonth(CustomUser user, int date) {
        return expensesDAO.findAllExpensesUserInTheTime(user, date);
    }
}
