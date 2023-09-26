package com.banking.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BankApplication {

	@GetMapping("/")
	String home() {
		return "API is working";
	}

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
