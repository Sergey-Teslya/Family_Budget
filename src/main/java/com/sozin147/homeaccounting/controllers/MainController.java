package com.sozin147.homeaccounting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/")
    public String mainPage(){
        return "index";
    }

    @RequestMapping("/user")
    public String userPage(){
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/completeRegistration")
    public String completeRegistrationPage() {
        return "completeRegistration";
    }

    @RequestMapping("/diagram")
    public String diagramPage(){
        return "diagram";
    }

}
