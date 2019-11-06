package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.QuantityDay;
import com.sozin147.homeaccounting.services.UserService;
import com.sozin147.homeaccounting.services.impl.AddToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//Указывает что принимает и возвращает только JSON, без vue части
@RestController
public class DiagramRESTController {

    @Autowired
    private AddToJson addToJson;

    @Autowired
    private UserService userService;

    @RequestMapping("/dataPieChartDiagramOneWeek")
    public String dataPieChartDiagramOneWeek(@AuthenticationPrincipal User activeUser) throws IOException {
        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToPieChartDiagram(user, QuantityDay.ONE_WEEK.day);
    }

    @RequestMapping("/dataColumnChartDiagramOneWeek")
    public String dataColumnChartDiagramOneWeek(@AuthenticationPrincipal User activeUser) throws IOException {
//        String test = "{\n" +
//                "  \"cols\": [\n" +
//                "    {\"label\":\"Topping\",\"type\":\"string\"},\n" +
//                "    {\"label\":\"Price\",\"type\":\"number\"},\n" +
//                "    {\"label\":\"Price\",\"type\":\"number\"},\n" +
//                "    {\"label\":\"Price\",\"type\":\"number\"},\n" +
//                "    {\"label\":\"Price\",\"type\":\"number\"},\n" +
//                "    {\"label\":\"Price\",\"type\":\"number\"}\n" +
//                "  ],\n" +
//                "  \"rows\": [\n" +
//                "    {\"c\":[{\"v\":\"Mushrooms\"},{\"v\":3},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1}]},\n" +
//                "    {\"c\":[{\"v\":\"Onions\"},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1}]},\n" +
//                "    {\"c\":[{\"v\":\"Olives\"},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1}]},\n" +
//                "    {\"c\":[{\"v\":\"Zucchini\"},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1}]},\n" +
//                "    {\"c\":[{\"v\":\"Pepperoni\"},{\"v\":2},{\"v\":1},{\"v\":1},{\"v\":1},{\"v\":1}]}\n" +
//                "  ]\n" +
//                "}";
//
//        return test;

        CustomUser user = userService.getUserByLogin(activeUser.getUsername());

        return addToJson.addDataInJsonToColumnChartDiagram(user, QuantityDay.ONE_WEEK.day);
    }

}
