package com.project.lingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LingoApplication.class, args);
	}
}
