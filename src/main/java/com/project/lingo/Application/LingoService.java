package com.project.lingo.Application;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LingoService implements ILingoService {
    private SpelRepository spelRepository;
    private Lingo lingo = new Lingo();
    private LingoMet2Spelers lingoMet2Spelers = new LingoMet2Spelers();

    public LingoService(SpelRepository repository){
        this.spelRepository = repository;
    }

    //vanaf hier de database aanroepen

    @Override
    public Lingo start(int lengthWord, Speler speler) {
        if (speler != null){
            lingo.setSpel(nieuwSpelMetGebruiker(speler));
        } else {
            lingo.setSpel(nieuwSpel());
        }
        lingo.setTeRadenWoord(lengthWord);
        spelRepository.save(lingo.getSpel());
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
    public Spel nieuwSpel(){
        Builder builder = new SpelBuilder();
        builder.setTotaalPunten(0);
        builder.setDatum(new Date());
        return builder.build();
    }

    @Override
    public Spel nieuwSpelMetGebruiker(Speler speler){
        Builder builder = new SpelBuilder();
        builder.setTotaalPunten(0);
        builder.setDatum(new Date());
        builder.setSpeler(speler);
        spelRepository.save(builder.build());
        return builder.build();
    }

    @Override
    public Spel nieuwePoging(String woordVanSpeler, long gameId){
        return new Spel();
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
