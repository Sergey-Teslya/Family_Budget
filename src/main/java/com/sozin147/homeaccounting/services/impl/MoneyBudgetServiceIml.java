package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.MoneyBudgetDAO;
import com.sozin147.homeaccounting.model.MoneyBudget;
import com.sozin147.homeaccounting.services.MoneyBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyBudgetServiceIml implements MoneyBudgetService {

    @Autowired
    private MoneyBudgetDAO moneyDAO;

    @Override
    @Transactional
    public void addMoney(MoneyBudget money) {
        moneyDAO.save(money);
    }
}
