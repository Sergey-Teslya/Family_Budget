package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.DAO.CustomUserDAO;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomUserDAO customUserDAO;

    @Override
    public boolean activateUser(String code) {
        CustomUser user = customUserDAO.findByActivationCode(code);
        if (user == null){
            return false;
        }

        user.setActivationCode(null);
        user.setActive(true);
        customUserDAO.save(user);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public String getUserEmail(String email) {
        return customUserDAO.getEmail(email.toLowerCase());
    }

    @Override
    @Transactional(readOnly = true)
    public String getUserLogin(String login) {
        return customUserDAO.getLogin(login.toLowerCase());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomUser getUserByLogin(String login) {
        return customUserDAO.findByLogin(login.toLowerCase());
    }

    @Override
    @Transactional
    public void addUser(CustomUser customUser) {
        customUserDAO.save(customUser);
    }
}
