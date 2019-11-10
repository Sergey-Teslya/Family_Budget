package com.sozin147.homeaccounting.services;

import com.sozin147.homeaccounting.model.CustomUser;

public interface UserService {
    CustomUser getUserByLogin(String login);

    String getUserEmail(String email);

    String getUserLogin(String login);

    void saveUser(CustomUser customUser);

    boolean activateUser(String code);
}
