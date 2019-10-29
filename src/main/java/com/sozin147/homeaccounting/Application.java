package com.sozin147.homeaccounting;

import com.sozin147.homeaccounting.model.UserRole;
import com.sozin147.homeaccounting.model.CustomUser;
import com.sozin147.homeaccounting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(final UserService client){
		return (arg) -> {

//			sendEmail();
			client.addUser(new CustomUser("admin", "Sozin147@gmail.com","123", UserRole.ADMIN, true));
//			client.addUser(new CustomUser("user", "Tesly147@gmail.com","123", UserRole.USER, true));
		};
	}


//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	void sendEmail() {
//
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo("teslya147@gmail.com");
//
//		msg.setSubject("Testing from Spring Boot");
//		msg.setText("Hello World \n Spring Boot Email");
//
//		javaMailSender.send(msg);
//
//	}

}
