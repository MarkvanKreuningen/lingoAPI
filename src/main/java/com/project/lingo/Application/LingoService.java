package com.project.lingo.Application;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class LingoService {
    Lingo lingo = new Lingo();
    LingoMet2Spelers lingoMet2Spelers = new LingoMet2Spelers();

    @Autowired
    SpelRepository spelRepository;

    //vanaf hier de database aanroepen

    public String start(int lengthWord) {
        lingo.setSpel(nieuwSpel());
        lingo.setTeRadenWoord(lengthWord);
        return lingo.start();
    }

    public String spelerSpeelt(String woordVanSpeler) {
        lingo.setBeurt();
        lingo.setWoordVanSpeler(woordVanSpeler);
        return lingo.spelerSpeelt(60);
    }

    public Spel nieuwSpel(){
        Builder builder = new SpelBuilder();
        builder.setTotaalPunten(0);
        builder.setDatum(new Date());
        return builder.build();
    }

    public Spel nieuwePoging(String woordVanSpeler, long gameId){
        return new Spel();
    }

    public LingoMet2Spelers gameMet2Spelers(String naamSpeler1, String naamSpeler2, int tijdPerBeurt){
        lingoMet2Spelers = new LingoMet2Spelers(naamSpeler1, naamSpeler2, tijdPerBeurt);
        lingoMet2Spelers.setTeRadenWoord(5);
        System.out.println(lingoMet2Spelers.toString());
        return lingoMet2Spelers;
    }

    public String nieuwWoord(){
        return "";
    }


}
