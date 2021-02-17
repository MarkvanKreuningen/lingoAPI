package com.project.lingo.Domain;

import java.util.Date;

public interface Builder {
    void setTotaalPunten(int punten);
    void setDatum(Date datum);
    void setSpeler(Speler speler);
    void addToList(Poging poging);
    Spel build();
}
