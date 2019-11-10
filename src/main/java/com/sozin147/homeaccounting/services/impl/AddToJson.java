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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddToJson {

    @Autowired
    private ExpensesUserService expenses;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriesBudgetService categories;


    public String addDataInJsonToPieChartDiagram(CustomUser user, int date) throws IOException {
        List<CategoriesBudget> categoriesList = categories.getAllCategories();
        List<ExpensesUser> expensesList = expenses.findAllExpensesForDate(user, date);

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


        DataTableDiagramParsInJson dataTableDiagramParsInJson = new DataTableDiagramParsInJson(cols, rows);

        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(dataTableDiagramParsInJson);
    }


    public String addDataInJsonToColumnChartDiagramLastWeek(CustomUser user, int date) throws IOException {
        List<CategoriesBudget> categoriesList = categories.getAllCategories();
        List<ExpensesUser> expensesList = expenses.findAllExpensesForDate(user, date);
        List<Rows> rows = new ArrayList<>();
        Set<Date> dateOfDay = new TreeSet<>();

        for (ExpensesUser expensesUser : expensesList) {
            dateOfDay.add(expensesUser.getDate());
        }

        List<Cols> cols = new ArrayList<>();
        cols.add(new Cols("Topping", "string"));
        for (CategoriesBudget category : categoriesList) {
            cols.add(new Cols(category.getName(), "number"));
        }

        for (Date day : dateOfDay) {
            List<Cells> cells = new ArrayList<>();
            cells.add(new Cells(dayOfWeek(day)));

            for (CategoriesBudget category : categoriesList) {
                long count = 0;

                for (ExpensesUser expensesUser : expensesList) {
                    if (day.equals(expensesUser.getDate())) {

                        if (category.equals(expensesUser.getCategory())) {
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

    public String addDataInJsonToColumnChartDiagramLastMonth(CustomUser user, int date) throws IOException {
        final int WEEK_OF_MONTH = 4;
        List<CategoriesBudget> categoriesList = categories.getAllCategories();
        List<ExpensesUser> expensesList = expenses.findAllExpensesForDate(user, date);
        List<Cols> cols = new ArrayList<>();
        List<Rows> rows = new ArrayList<>();

        Set<Date> setDateOfDay = new TreeSet<>();
        for (ExpensesUser expensesUser : expensesList) {
            setDateOfDay.add(expensesUser.getDate());
        }
        List<Date> listDateOfDay = new ArrayList<>(setDateOfDay);

        cols.add(new Cols("Topping", "string"));
        for (CategoriesBudget category : categoriesList) {
            cols.add(new Cols(category.getName(), "number"));
        }

        for (int i = 0; i < WEEK_OF_MONTH; i++) {
            List<Date> dayOFWeek = listDateOfDay.stream()
                    .filter(n -> listDateOfDay.indexOf(n) < 7)
                    .collect(Collectors.toList());

            List<Cells> cells = new ArrayList<>();
            if (dayOFWeek.size() > 0)
                cells.add(new Cells(dateFormatToString(dayOFWeek.get(0)) +
                        " / " + dateFormatToString(dayOFWeek.get(dayOFWeek.size() - 1))));
            else
                cells.add(new Cells(" "));

            for (CategoriesBudget category : categoriesList) {
                long count = 0;

                for (Date day : dayOFWeek) {
                    for (ExpensesUser expensesUser : expensesList) {
                        if (day.equals(expensesUser.getDate())) {

                            if (category.equals(expensesUser.getCategory())) {
                                count += expensesUser.getMoney();
                            }
                        }
                    }
                }
                cells.add(new Cells(count));
            }
            listDateOfDay.removeAll(dayOFWeek);

            rows.add(new Rows(cells));
        }
        DataTableDiagramParsInJson dataTableDiagramParsInJson = new DataTableDiagramParsInJson(cols, rows);

        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(dataTableDiagramParsInJson);
    }

    private String dateFormatToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM", Locale.US);
        return simpleDateFormat.format(date);
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
