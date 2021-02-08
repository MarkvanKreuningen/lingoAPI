package com.project.lingo.Application;

import com.project.lingo.Domain.Lingo;

public class LingoService {
    Lingo lingo = new Lingo();

    //vanaf hier de database aanroepen

    public String start(int lengthWord) {
        lingo.setTeRadenWoord(lengthWord);
        lingo.setBeurt();
        lingo.setWoordGeraden(false);
        return String.valueOf(lingo.getTeRadenWoord().charAt(0));
    }

    public String spelerSpeelt(String raadbeurt) {
        lingo.setBeurt();
        lingo.setRaadBeurt(raadbeurt);
        return lingo.getFeedback();
    }

}
