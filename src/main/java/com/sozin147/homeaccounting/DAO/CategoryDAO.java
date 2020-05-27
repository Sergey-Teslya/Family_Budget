package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
