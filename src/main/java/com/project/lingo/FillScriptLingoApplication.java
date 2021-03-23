package com.project.lingo;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
@Component
public class FillScriptLingoApplication {


	@Autowired
	private SpelerRepository spelerRepository;

	@Autowired
	private SpelRepository spelRepository;

	@PostConstruct
	public void main() throws Exception {
		/*Speler speler = new Speler();
		speler.setEmail("welkom@gmail.com");
		speler.setGebruikersnaam("Mark3");
		speler.setWachtwoord("$2a$10$.DucKyOjNqQTSEDJ//CaeOPlMJwauoXdXxSWQDGPvqRojr.c4LQEC");
		speler.setRol("GUEST");
		spelerRepository.save(speler);

		Builder builder = new SpelBuilder();
		builder.setTotaalPunten(5);
		builder.setDatum(new Date());
		builder.addToList(new Poging(1, "abdce", "aarde"));
		builder.addToList(new Poging(2, "abcde", "aarde"));
		builder.addToList(new Poging(3, "abcde", "aarde"));
		builder.addToList(new Poging(4, "abcde", "aarde"));
		builder.addToList(new Poging(5, "aarde", "aarde"));
		builder.setSpeler(spelerRepository.findSpelerByEmail("welkom@gmail.com"));
		spelRepository.save(builder.build());*/
	}

}
