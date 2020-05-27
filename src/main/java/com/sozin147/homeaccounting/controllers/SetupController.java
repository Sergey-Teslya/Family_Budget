package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.DAO.CategoryDAO;
import com.sozin147.homeaccounting.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SetupController {

  private final CategoryDAO category;

  public SetupController(CategoryDAO category) {
    this.category = category;
  }

  @GetMapping("/setup")
  private String setup() {
    String[] categoryName = {"Досуг", "Покупки", "Транспорт", "Продукты", "Здоровье", "Семья"};
    for (String name : categoryName) {
      category.save(new Category(name));
    }

    return "user";
  }
}
