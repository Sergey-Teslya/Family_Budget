package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.CategoriesBudget;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;
import com.sozin147.homeaccounting.model.MoneyBudget;
import com.sozin147.homeaccounting.services.CategoriesBudgetService;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import com.sozin147.homeaccounting.services.MoneyBudgetService;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class AddDataUserController {

    @Autowired
    private MoneyBudgetService moneyBudgetService;

    @Autowired
    private ExpensesUserService expensesUserService;

    @Autowired
    private CategoriesBudgetService categoriesBudgetService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add_money_budget", method = RequestMethod.POST)
    public String addMoneyBudget(@RequestParam Integer money, @AuthenticationPrincipal User activeUser) {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        MoneyBudget moneyBudget = moneyBudgetService.getMoneyBudgetUser(user);
        Integer moneyInBudgetUser = moneyBudget.getMoney();
        moneyInBudgetUser += money;
        moneyBudget.setMoney(moneyInBudgetUser);

        moneyBudgetService.updateMoneyBudget(moneyBudget);

        return "redirect:/costs";
    }

    @RequestMapping(value = "/add_expenses", method = RequestMethod.POST)
    public String addExpensesUser(@RequestParam Integer money, @RequestParam long category,
                                  @RequestParam String stringDate, @RequestParam String comment,
                                  @AuthenticationPrincipal User activeUser, Model model) throws ParseException {
        if (category == 0) {
            return "redirect:/costs?error";
        }

        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        Optional<CategoriesBudget> categoriesBudget = categoriesBudgetService.getCategoryById(category);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = dateFormat.parse(stringDate);
        expensesUserService.addExpenses(new ExpensesUser(user, categoriesBudget.get(), money, comment, date));

        MoneyBudget moneyBudget = moneyBudgetService.getMoneyBudgetUser(user);
        Integer moneyInBudgetUser = moneyBudget.getMoney();
        moneyInBudgetUser -= money;
        moneyBudget.setMoney(moneyInBudgetUser);

        moneyBudgetService.updateMoneyBudget(moneyBudget);
        return "redirect:/costs";
    }

}
