package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.BudgetCategory;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.QuantityDay;
import com.sozin147.homeaccounting.services.BudgetCategoryService;
import com.sozin147.homeaccounting.services.UserService;
import com.sozin147.homeaccounting.services.impl.AddToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

//Указывает что принимает и возвращает только JSON, без vue части
@RestController
public class DiagramRESTController {

    @Autowired
    private AddToJson addToJson;

    @Autowired
    private UserService userService;

    @Autowired
    private BudgetCategoryService budgetCategoryService;

    @RequestMapping("/dataPieChartDiagramOneDay")
    public String dataPieChartDiagramOneDay(@AuthenticationPrincipal User activeUser) throws IOException {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToPieChartDiagram(user, QuantityDay.ONE_DAY.day);
    }

    @RequestMapping("/dataPieChartDiagramOneWeek")
    public String dataPieChartDiagramOneWeek(@AuthenticationPrincipal User activeUser) throws IOException {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToPieChartDiagram(user, QuantityDay.ONE_WEEK.day);
    }

    @RequestMapping("/dataPieChartDiagramOneMonth")
    public String dataPieChartDiagramOneMonth(@AuthenticationPrincipal User activeUser) throws IOException {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToPieChartDiagram(user, QuantityDay.ONE_MONTH.day);
    }

    @RequestMapping("/dataColumnChartDiagramOneWeek")
    public String dataColumnChartDiagramOneWeek(@AuthenticationPrincipal User activeUser) throws IOException {

        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToColumnChartDiagramLastWeek(user, QuantityDay.ONE_WEEK.day);
    }

    @RequestMapping("/dataColumnChartDiagramMonth")
    public String dataColumnChartDiagramOneMonth(@AuthenticationPrincipal User activeUser) throws IOException {

        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToColumnChartDiagramLastMonth(user, QuantityDay.ONE_MONTH.day);
    }

  @RequestMapping("/categoryBudget")
  public List<BudgetCategory> dataCategoryBudget(@AuthenticationPrincipal User activeUser) {
    CustomUser user = userService.getUserByLogin(activeUser.getUsername());
    budgetCategoryService.getCategories(user);
    return budgetCategoryService.getCategories(user);
  }
}
