package com.project.lingo;

import com.project.lingo.data.repository.GameRepository;
import com.project.lingo.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FillScriptLingoApplication {


	@Autowired
	private UserRepository spelerRepository;

	@Autowired
	private GameRepository gameRepository;

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
