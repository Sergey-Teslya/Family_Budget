package com.sozin147.homeaccounting;

import com.sozin147.homeaccounting.DAO.CategoriesBudgetDAO;
import com.sozin147.homeaccounting.model.CategoriesBudget;
import com.sozin147.homeaccounting.services.ExpensesUserService;
import com.sozin147.homeaccounting.services.MoneyBudgetService;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner demo(final UserService client, final CategoriesBudgetDAO category, final MoneyBudgetService money, final ExpensesUserService expenses) {
        return (arg) -> {

            String[] categoryName = {"Досуг", "Покупки", "Транспорт", "Продукты", "Здоровье", "Семья"};
            for (String name : categoryName) {
                category.save(new CategoriesBudget(name));
            }

        };
    }


}
