package com.sozin147.homeaccounting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class CustomUser {
    @Id
    private String login;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean active;
    private String activationCode;


    public CustomUser(String login, String email, String password, UserRole role, boolean active) {
        this.login = login.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public CustomUser(String login, String email, String password, UserRole role, boolean active, String activationCode) {
        this.login = login.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
        this.role = role;
        this.active = active;
        this.activationCode = activationCode;
    }

}
