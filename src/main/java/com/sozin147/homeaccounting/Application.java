package com.sozin147.homeaccounting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sozin147.homeaccounting.DAO.CategoriesBudgetDAO;
import com.sozin147.homeaccounting.model.*;
import com.sozin147.homeaccounting.parserInJson.cols.Cols;
import com.sozin147.homeaccounting.parserInJson.rows.Cells;
import com.sozin147.homeaccounting.parserInJson.rows.Rows;
import com.sozin147.homeaccounting.parserInJson.DataTableDiagramParsInJson;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import com.sozin147.homeaccounting.services.MoneyBudgetService;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public CommandLineRunner demo(final UserService client, final CategoriesBudgetDAO category, final MoneyBudgetService money, final ExpensesUserService expenses){
		return (arg) -> {
			CustomUser testUser = new CustomUser("test", "test@gmail.com", "123", UserRole.USER, true);
			CustomUser testUser1 = new CustomUser("test1", "test@gmail.com1", "123", UserRole.USER, true);

			client.addUser(new CustomUser("admin", "Sozin147@gmail.com","123", UserRole.ADMIN, true));
			client.addUser(new CustomUser("user", "Tesly147@gmail.com","123", UserRole.USER, true));
			client.addUser(testUser);
			client.addUser(testUser1);

			String[] categoryName = {"Досуг", "Покупки", "Транспорт", "Продукты", "Здоровье", "Семья"};
			for (String name : categoryName){
				category.save(new CategoriesBudget(name));
			}


			money.addMoney(new MoneyBudget(testUser, 2000));
			money.addMoney(new MoneyBudget(testUser, 2000));
			money.addMoney(new MoneyBudget(testUser, 2000));

			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Досуг"), 200, "new cost", new Date(119, Calendar.NOVEMBER,2)));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Семья"), 100, "new cost", new Date(119, Calendar.NOVEMBER,2)));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Покупки"), 50, "new cost", new Date(119, Calendar.NOVEMBER,2)));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Семья"), 400, "new cost", new Date(119, Calendar.NOVEMBER,4)));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Покупки"), 300, "new cost", new Date(119, Calendar.NOVEMBER,4)));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Досуг"), 600, "new cost", new Date(119, Calendar.NOVEMBER,4)));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Покупки"), 200, "new cost", new Date()));
			expenses.addExpenses(new ExpensesUser(testUser, category.findByName("Покупки"), 200, "new cost", new Date()));

			expenses.addExpenses(new ExpensesUser(testUser1, category.findByName("Семья"), 200, "new cost", new Date()));

			List<ExpensesUser> expensesUsersList = expenses.findAllExpensesByUser(testUser1);

			ObjectMapper objectMapper = new ObjectMapper();

			List<Cols> cols = new ArrayList<>();
			cols.add(new Cols("Topping", "string"));
			cols.add(new Cols("Slices", "number"));

			List<Cells> cells = new ArrayList<>();
			List<Cells> cells1 = new ArrayList<>();
			cells.add(new Cells("Mushrooms"));
			cells.add(new Cells(3));

			cells1.add(new Cells("test"));
			cells1.add(new Cells(1));

			List<Rows> rows = new ArrayList<>();
			rows.add(new Rows(cells));
			rows.add(new Rows(cells1));

			DataTableDiagramParsInJson dataTableDiagramParsInJson = new DataTableDiagramParsInJson(cols, rows);


			String jsonString = objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(dataTableDiagramParsInJson);

//			Files.write(Paths.get("src\\main\\java\\com\\sozin147\\homeaccounting\\parser\\json.json")
//					,jsonString.getBytes());


//			List<ExpensesUser> ttt = expenses.getExpensesForLastWeek(testUser);
//
//			System.out.println(expensesUsersList);
//			System.out.println(ttt);
		};
	}


}
