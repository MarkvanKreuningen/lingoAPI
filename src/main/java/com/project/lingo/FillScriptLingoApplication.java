package com.project.lingo;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
@SpringBootApplication
public class FillScriptLingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LingoApplication.class, args);
	}

@Autowired
	private SpelerRepository spelerRepository;

	@Autowired
	private SpelRepository spelRepository;

	public void run(String... args) throws Exception {
		Speler speler = new Speler();
		speler.setEmail("markvankreuningen@gmail.com");
		speler.setGebruikersnaam("Mark2");
		speler.setWachtwoord("$2a$10$.DucKyOjNqQTSEDJ//CaeOPlMJwauoXdXxSWQDGPvqRojr.c4LQEC");
		speler.setRol("GUEST");
		spelerRepository.save(speler);

		/*Builder builder = new SpelBuilder();
		builder.setTotaalPunten(5);
		builder.setDatum(new Date());
		builder.addToList(new Poging(1, "abdce", "aarde"));
		builder.addToList(new Poging(2, "abcde", "aarde"));
		builder.addToList(new Poging(3, "abcde", "aarde"));
		builder.addToList(new Poging(4, "abcde", "aarde"));
		builder.addToList(new Poging(5, "aarde", "aarde"));
		builder.setSpeler(spelerRepository.findByGebruikersnaam("Mark"));
		spelRepository.save(builder.build());*/
	}

}
