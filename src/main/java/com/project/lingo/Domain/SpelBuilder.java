package com.project.lingo.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpelBuilder implements Builder {
    private int totaalPunten;
    private Date datum;
    private Speler speler;
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
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void addToList(Poging poging) {
        this.pogingList.add(poging);
    }

    @Override
    public Spel build() {
        Spel spel = new Spel();
        spel.setTotaalPunten(totaalPunten);
        spel.setDatum(datum);
        spel.setSpeler(speler);
        spel.setPogingen(pogingList);
        return spel;
    }
}
