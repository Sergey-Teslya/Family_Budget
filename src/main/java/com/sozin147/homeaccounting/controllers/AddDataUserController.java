package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.*;
import com.sozin147.homeaccounting.services.*;
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
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BudgetCategoryService budgetCategoryService;

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

        Optional<Category> categoriesBudget = categoryService.getCategoryById(category);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = dateFormat.parse(stringDate);
        expensesUserService.addExpenses(new ExpensesUser(user, categoriesBudget.get(), money, comment, date));

        MoneyBudget moneyBudget = moneyBudgetService.getMoneyBudgetUser(user);
        Integer moneyInBudgetUser = moneyBudget.getMoney();
        moneyInBudgetUser -= money;
        moneyBudget.setMoney(moneyInBudgetUser);

        BudgetCategory categoryBudget = budgetCategoryService.getCategoryBudget(categoriesBudget.get(), user);
        Integer moneyCategory = categoryBudget.getMoney();
        moneyCategory -= money;
        categoryBudget.setMoney(moneyCategory);
        budgetCategoryService.create(categoryBudget);

        moneyBudgetService.updateMoneyBudget(moneyBudget);
        return "redirect:/costs";
    }

    @RequestMapping(value = "/budget/category", method = RequestMethod.POST)
    public String createBudgetCategory(@RequestParam Integer money, @RequestParam long category,
                                       @AuthenticationPrincipal User activeUser) {

      if (category == 0) {
        return "redirect:/costs?error";
      }

      CustomUser user = userService.getUserByLogin(activeUser.getUsername());
      Optional<Category> categoryBudget = categoryService.getCategoryById(category);
      budgetCategoryService.create(new BudgetCategory(categoryBudget.get(), user, money));

      return "redirect:/costs";
    }

}
