package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//Это сервис типа адаптера для представления нашей учетной записии в спринг
//Трансформирует наше представление в сприноговое
//Конвертируем наши хранимые данные в базе в его представление
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    //Каждый раз когда спрингу нужна будет информация об учетной записи
    //он будет дергать этот метод и передавать сюда логи
    //(Дай по логину информацию об учетной записи)
    //Учетная запись представляеться как реализация спригнового интерфейса UserDetails
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //Спомощю нашего сервиса по логину пытаемся досать объект CustomUser(наше представление учетной записи)
        CustomUser customUser = userService.getUserByLogin(login);

        //Бросаем исключение если логина нет
        if (customUser == null)
            throw new UsernameNotFoundException(login + " not found");

        //Сюда добавляем нашу роль и после заворачиваем в спринговый класс
        //Можно использовать любую колекцию
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(customUser.getRole().toString()));

        //Возвращаем класс User(это спринговый класс реализующий интерфейс UserDetails)
        //Представляет что такое пользователь в удобном для спринга виде
        //В конструктор передаем логин, пароль, роль(этого юзера в программе )
        return new User(customUser.getLogin(), customUser.getPassword(), roles);
    }
}