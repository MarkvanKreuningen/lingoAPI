package com.project.lingo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameBuilder implements Builder {
    private int totaalPunten;
    private Date datum;
    private User user;
    private List<Attempt> attemptList = new ArrayList<>();

    @Override
    public void setTotaalPunten(int punten) {
        this.totaalPunten += punten;
    }

    @Override
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void addToList(Attempt attempt) {
        this.attemptList.add(attempt);
    }

    @Override
    public Game build() {
        Game game = new Game();
        game.setTotalPoints(totaalPunten);
        game.setDate(datum);
        game.setUser(user);
        game.setAttempts(attemptList);
        return game;
    }
}
