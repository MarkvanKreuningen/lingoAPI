package com.project.lingo;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Builder;
import com.project.lingo.Domain.Poging;
import com.project.lingo.Domain.SpelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class LingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LingoApplication.class, args);
	}
}
