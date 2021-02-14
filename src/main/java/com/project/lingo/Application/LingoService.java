package com.project.lingo.Application;

import com.project.lingo.Domain.Lingo;

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

}
