package com.sozin147.homeaccounting.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sozin147.homeaccounting.model.CategoriesBudget;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;
import com.sozin147.homeaccounting.parser.DataTableDiagramPars;
import com.sozin147.homeaccounting.parser.cols.Cols;
import com.sozin147.homeaccounting.parser.rows.Cells;
import com.sozin147.homeaccounting.parser.rows.Rows;
import com.sozin147.homeaccounting.services.CategoriesBudgetService;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddToJson {

    @Autowired
    private ExpensesUserService expenses;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriesBudgetService categories;


    public String addDataInJsonToPieChartDiagramFromLastWeek(CustomUser user) throws IOException {
        List<CategoriesBudget> categoriesList = categories.getAll();
        List<ExpensesUser> expensesList = expenses.findAllExpensesForLastWeek(user);

        List<Cols> cols = new ArrayList<>();
        cols.add(new Cols("Topping", "string"));
        cols.add(new Cols("Slices", "number"));

        List<Rows> rows = new ArrayList<>();

        for (CategoriesBudget category : categoriesList){
            long count = 0;
            for (ExpensesUser expensesUser : expensesList){
                if (category.equals(expensesUser.getCategory())){
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

        DataTableDiagramPars dataTableDiagramPars = new DataTableDiagramPars(cols, rows);

        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(dataTableDiagramPars);
    }

}
