package com.sozin147.homeaccounting.services.impl;

import com.sozin147.homeaccounting.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    private final String LINK_ACTIVE = "http://localhost:8080/activate/";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessageMail(CustomUser user){
        if (user != null){
            String message = String.format("Hello, %s! \n" +
                    "Welcome to Home Accounting. Please, visit next link " + LINK_ACTIVE + "%s",
                    user.getLogin(),
                    user.getActivationCode()
            );

            templateSendMessage(user.getEmail(), message);
        }
    }

    private void templateSendMessage(String sendTo, String message){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(sendTo);
        msg.setSubject("Activation code");
        msg.setText(message);

        javaMailSender.send(msg);
    }

}
