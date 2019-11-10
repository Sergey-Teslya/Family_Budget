package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.*;
import com.sozin147.homeaccounting.services.CategoriesBudgetService;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import com.sozin147.homeaccounting.services.MoneyBudgetService;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpensesUserService expensesUserService;

    @Autowired
    private CategoriesBudgetService categoriesBudget;

    @Autowired
    private MoneyBudgetService moneyBudgetService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/costs")
    public String costsPage(@AuthenticationPrincipal User activeUser, Model model) {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        List<CategoriesBudget> categoriesList = categoriesBudget.getAllCategories();
        MoneyBudget moneyBudget = moneyBudgetService.getMoneyBudgetUser(user);

        if (moneyBudget == null)
            moneyBudgetService.addMoneyBudget(new MoneyBudget(user, 0));

        long money = moneyBudgetService.getMoney(user);
        long sumExpensesForLastMonth = expensesUserService.getSumExpensesUserForLastMonth(user);

        model.addAttribute("sumExpensesForLastMonth", sumExpensesForLastMonth);
        model.addAttribute("listCategories", categoriesList);
        model.addAttribute("money", money);
        return "costs";
    }

    @RequestMapping("/table")
    public String tablePage(@AuthenticationPrincipal User activeUser, Model model) {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());
        List<ExpensesUser> expensesUserList = expensesUserService.findAllExpensesForDate(user, QuantityDay.ONE_MONTH.day);
        Collections.reverse(expensesUserList);

        model.addAttribute("expensesUser", expensesUserList);

        return "table";
    }

    @GetMapping("/completeRegistration")
    public String completeRegistrationPage() {
        return "completeRegistration";
    }

    @RequestMapping("/diagram")
    public String diagramPage() {
        return "diagram";
    }

}
