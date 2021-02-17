package com.project.lingo.Application;

import com.project.lingo.Domain.Builder;
import com.project.lingo.Domain.Lingo;
import com.project.lingo.Domain.SpelBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LingoService {
    Lingo lingo = new Lingo();

    //vanaf hier de database aanroepen

    public String start(int lengthWord) {
        lingo.setTeRadenWoord(lengthWord);
        return lingo.start();
    }

    public String spelerSpeelt(String woordVanSpeler) {
        lingo.setBeurt();
        lingo.setWoordVanSpeler(woordVanSpeler);
        return lingo.spelerSpeelt();
    }

    public void nieuwSpel(){
        Builder builder = new SpelBuilder();
        builder.setTotaalPunten(0);
        builder.setDatum(new Date());
        builder.build();
    }

    public void nieuwePoging(String woordVanSpeler, long gameId){

    }


}
