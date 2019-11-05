package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.model.ExpensesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpensesUserDAO extends JpaRepository<ExpensesUser, Long> {

    @Query("SELECT exp FROM ExpensesUser exp WHERE exp.user = :user AND exp.date > current_date - 7")
    List<ExpensesUser> findAllActiveExpensesUsersInWeek(@Param("user") CustomUser user);

    @Query("SELECT exp FROM ExpensesUser exp WHERE exp.user = :user AND exp.date > current_date - 30")
    List<ExpensesUser> findAllActiveExpensesUsersInMonth(@Param("user") CustomUser user);

    @Query("SELECT exp FROM ExpensesUser exp WHERE exp.user = :user")
    List<ExpensesUser> getExpensesByUserLogin(@Param("user") CustomUser user);
}
