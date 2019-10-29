package com.sozin147.homeaccounting.controllers;

import com.sozin147.homeaccounting.model.UserRole;
import com.sozin147.homeaccounting.filter.CheckRegistrationData;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.services.UserService;
import com.sozin147.homeaccounting.services.impl.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckRegistrationData checkRegistrationData;

    @Autowired
    private EmailSender emailSender;

    @RequestMapping(value = "/user_registration", method = RequestMethod.POST)
    public String registerUser(@RequestParam(value = "user_login") String login,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {

        boolean checkEmail = checkRegistrationData.checkEmailForDuplicates(email);
        boolean checkLogin = checkRegistrationData.checkLoginForDuplicates(login);
        boolean checkData = checkRegistrationData.checkDataForNull(login, email, password);

        if (checkEmail || checkLogin || checkData) {

            model.addAttribute("checkEmail", checkEmail);
            model.addAttribute("checkLogin", checkLogin);
            model.addAttribute("checkData", checkData);

            return "registration";
        }

        CustomUser user = new CustomUser(login, email, password, UserRole.USER, false, UUID.randomUUID().toString());
        userService.addUser(user);
        emailSender.sendMessageMail(user);

        return "redirect:/completeRegistration";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model){
        boolean isActivate = userService.activateUser(code);

        if (isActivate){
            model.addAttribute("activate", "Account Activate");
        } else {
            model.addAttribute("activate", "Activation code is not found");
        }

        return "activateAccountPage";
    }

}
