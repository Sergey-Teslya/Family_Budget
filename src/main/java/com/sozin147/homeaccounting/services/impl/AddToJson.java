package com.sozin147.homeaccounting.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sozin147.homeaccounting.model.CategoriesBudget;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;
import com.sozin147.homeaccounting.parserInJson.DataTableDiagramParsInJson;
import com.sozin147.homeaccounting.parserInJson.cols.Cols;
import com.sozin147.homeaccounting.parserInJson.rows.Cells;
import com.sozin147.homeaccounting.parserInJson.rows.Rows;
import com.sozin147.homeaccounting.services.CategoriesBudgetService;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AddToJson {

    @Autowired
    private ExpensesUserService expenses;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriesBudgetService categories;


    public String addDataInJsonToPieChartDiagram(CustomUser user, int date) throws IOException {
        List<CategoriesBudget> categoriesList = categories.getAll();
        List<ExpensesUser> expensesList = expenses.findAllExpensesForLastWeek(user, date);

        List<Cols> cols = new ArrayList<>();
        cols.add(new Cols("Topping", "string"));
        cols.add(new Cols("Slices", "number"));

        List<Rows> rows = new ArrayList<>();

        for (CategoriesBudget category : categoriesList) {
            long count = 0;
            for (ExpensesUser expensesUser : expensesList) {
                if (category.equals(expensesUser.getCategory())) {
                    count += expensesUser.getMoney();
                }

            }

            List<Cells> cells = new ArrayList<>();
            cells.add(new Cells(category.getName()));
            cells.add(new Cells(count));
            rows.add(new Rows(cells));
        }

//        List<Cells> cells = new ArrayList<>();
//        List<Cells> cells1 = new ArrayList<>();
//        cells.add(new Cells("Mushrooms"));
//        cells.add(new Cells(3));
//
//        cells1.add(new Cells("test"));
//        cells1.add(new Cells(1));
//
//        List<Rows> rows = new ArrayList<>();
//        rows.add(new Rows(cells));
//        rows.add(new Rows(cells1));

        DataTableDiagramParsInJson dataTableDiagramParsInJson = new DataTableDiagramParsInJson(cols, rows);

        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(dataTableDiagramParsInJson);
    }


    public String addDataInJsonToColumnChartDiagram(CustomUser user, int date) throws IOException {
        List<CategoriesBudget> categoriesList = categories.getAll();
        List<ExpensesUser> expensesList = expenses.findAllExpensesForLastWeek(user, date);

        List<Cols> cols = new ArrayList<>();
        cols.add(new Cols("Topping", "string"));
        for (CategoriesBudget category : categoriesList) {
            cols.add(new Cols(category.getName(), "number"));
        }

        List<Rows> rows = new ArrayList<>();

        Set<Date> setData = new TreeSet<>();
        for (ExpensesUser expensesUser : expensesList) {
            setData.add(expensesUser.getDate());
        }

        for (Date day : setData) {
            List<Cells> cells = new ArrayList<>();
            cells.add(new Cells(dayOfWeek(day)));

            for (CategoriesBudget category : categoriesList) {
                long count = 0;

                for (ExpensesUser expensesUser : expensesList){
                    if (day.equals(expensesUser.getDate())){

                        if (category.equals(expensesUser.getCategory())){
                            count += expensesUser.getMoney();
                        }

                    }
                }

                cells.add(new Cells(count));
            }
            rows.add(new Rows(cells));
        }

        DataTableDiagramParsInJson dataTableDiagramParsInJson = new DataTableDiagramParsInJson(cols, rows);

        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(dataTableDiagramParsInJson);
    }

    private String dayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "";
        }
    }

}
