package com.oceantech.oceantech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Controller
@EnableCaching
@EnableJpaRepositories(basePackages = "com.oceantech.oceantech.repository")
@OpenAPIDefinition(
	info = @Info(
		title = "Ocean Tech",
		summary = "API do App Ocean Tech",
		description = "Um app gamificado de estimulação de reciclagem",
		version = "1.0.0",
		contact = @Contact(name = "Felipe Santos Pinheiro", email = "fsp12371@gmail.com")
	)
)
public class OceantechApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceantechApplication.class, args);
	}

}
