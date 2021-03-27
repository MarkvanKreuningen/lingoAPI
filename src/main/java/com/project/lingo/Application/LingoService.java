package com.project.lingo.Application;

import com.project.lingo.Data.repository.GameRepository;
import com.project.lingo.Domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LingoService implements ILingoService {
    private GameRepository gameRepository;
    private Lingo lingo = new Lingo();
    private LingoMet2Spelers lingoMet2Spelers = new LingoMet2Spelers();

    public LingoService(GameRepository repository){
        this.gameRepository = repository;
    }

    //vanaf hier de database aanroepen

    @Override
    public Lingo start(int lengthWord, User user) {
        if (user != null){
            lingo.setSpel(nieuwSpelMetGebruiker(user));
        } else {
            lingo.setSpel(nieuwSpel());
        }
        lingo.setTeRadenWoord(lengthWord);
        gameRepository.save(lingo.getSpel());
        System.out.println(lingo.getSpel().toString());
        return lingo;
    }

    @Override
    public String spelerSpeelt(String woordVanSpeler) {
        lingo.setBeurt();
        lingo.setWoordVanSpeler(woordVanSpeler);
        return lingo.spelerSpeelt(60);
    }

    @Override
    public Game nieuwSpel(){
        Builder builder = new GameBuilder();
        builder.setTotaalPunten(0);
        builder.setDatum(new Date());
        return builder.build();
    }

    @Override
    public Game nieuwSpelMetGebruiker(User user){
        Builder builder = new GameBuilder();
        builder.setTotaalPunten(0);
        builder.setDatum(new Date());
        builder.setUser(user);
        gameRepository.save(builder.build());
        return builder.build();
    }

    @Override
    public Game nieuwePoging(String woordVanSpeler, long gameId){
        return (Game) gameRepository.findGamesForPlayerByUsername("Mark");
    }

    @Override
    public LingoMet2Spelers gameMet2Spelers(String naamSpeler1, String naamSpeler2, int tijdPerBeurt){
        lingoMet2Spelers = new LingoMet2Spelers(naamSpeler1, naamSpeler2, tijdPerBeurt);
        lingoMet2Spelers.setTeRadenWoord(5);
        System.out.println(lingoMet2Spelers.toString());
        return lingoMet2Spelers;
    }

    @Override
    public String nieuwWoord(){
        return "";
    }


}
