package com.project.lingo.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpelBuilder implements Builder {
    private int totaalPunten;
    private Date datum;
    private User user;
    private List<Poging> pogingList = new ArrayList<>();

    @Override
    public void setTotaalPunten(int punten) {
        this.totaalPunten += punten;
    }

    @Override
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public void setSpeler(User user) {
        this.user = user;
    }

    @Override
    public void addToList(Poging poging) {
        this.pogingList.add(poging);
    }

    @Override
    public Game build() {
        Game game = new Game();
        game.setTotalPoints(totaalPunten);
        game.setDate(datum);
        //game.setSpeler(user);
        game.setAttempts(pogingList);
        return game;
    }
}
