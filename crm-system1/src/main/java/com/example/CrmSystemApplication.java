package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Schema(description = "User entity")
@Component

@SpringBootApplication
@RestController
public class CrmSystemApplication {

	@GetMapping("/welcome")
	public String welcome() {
		return "Spring Boot Docker Demo";
	}

	public static void main(String[] args) {
		SpringApplication.run(CrmSystemApplication.class, args);
	}

}
