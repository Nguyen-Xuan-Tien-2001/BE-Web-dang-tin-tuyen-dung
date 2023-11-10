package com.example.Fiverr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
// @CrossOrigin(origins = "*")
@SpringBootApplication
@EnableScheduling

public class FiverrApplication {
	public static void main(String[] args) {
		SpringApplication.run(FiverrApplication.class, args);
	}
}
