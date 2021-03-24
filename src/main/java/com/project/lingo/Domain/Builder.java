package com.project.lingo.Domain;

import java.util.Date;

public interface Builder {
    void setTotaalPunten(int punten);
    void setDatum(Date datum);
    void setSpeler(User user);
    void addToList(Poging poging);
    Game build();
}
