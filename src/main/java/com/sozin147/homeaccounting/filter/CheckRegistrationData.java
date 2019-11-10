package com.sozin147.homeaccounting.filter;

import com.sozin147.homeaccounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CheckRegistrationData {

    @Autowired
    UserService userService;

    public boolean checkEmailForDuplicates(String email) {
        String userEmail = userService.getUserEmail(email);
        return userEmail != null;
    }

    public boolean checkLoginForDuplicates(String login) {
        String userLogin = userService.getUserLogin(login);
        return userLogin != null;
    }

    public boolean checkDataForNull(String login, String email, String password) {
        return StringUtils.isEmpty(login) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password);
    }

}
