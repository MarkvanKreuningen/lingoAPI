package com.project.lingo.application;

import com.project.lingo.domain.Lingo;

public class LingoService {
    Lingo lingo = new Lingo();

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
