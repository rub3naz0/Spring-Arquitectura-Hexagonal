package com.example.hexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HexagonalApplication {

	public static void main(String[] args) {
        System.out.println("Hexagonal Application is running...");
		SpringApplication.run(HexagonalApplication.class, args);
	}

}
