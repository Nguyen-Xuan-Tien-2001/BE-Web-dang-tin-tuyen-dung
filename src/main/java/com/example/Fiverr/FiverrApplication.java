package com.example.Fiverr;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Fiverr.Model.RoleEnum;
import com.example.Fiverr.Model.Users;
import com.example.Fiverr.Repository.UserRepository;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
// @CrossOrigin(origins = "*")
@SpringBootApplication
@EnableScheduling

public class FiverrApplication {
	public static void main(String[] args) {
		SpringApplication.run(FiverrApplication.class, args);
	}

	// @Bean
	// CommandLineRunner run(UserRepository userRepository,
	// 		PasswordEncoder passwordEncoder) {
	// 	return args -> {
	// 		Users admin = new Users(1L, "tien20062001@gmail.com", "admin","123456" ,RoleEnum.ADMIN);

	// 		userRepository.save(admin);
	// 	};
	// }
}
