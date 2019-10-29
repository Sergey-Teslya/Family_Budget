package com.sozin147.homeaccounting.DAO;

import com.sozin147.homeaccounting.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomUserDAO extends JpaRepository<CustomUser, String> {
    @Query("SELECT user FROM CustomUser user WHERE user.login = :login")
    CustomUser findByLogin(@Param("login") String login);

    @Query("SELECT email FROM CustomUser WHERE email = :email")
    String getEmail(@Param("email") String email);

    @Query("SELECT login FROM CustomUser WHERE login = :login")
    String getLogin(@Param("login") String login);

    CustomUser findByActivationCode(String code);
}
