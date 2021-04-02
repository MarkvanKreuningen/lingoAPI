package com.project.lingo.domain;

import java.util.Date;

public interface Builder {
    void setTotaalPunten(int punten);
    void setDatum(Date datum);
    void setUser(User user);
    void addToList(Attempt attempt);
    Game build();
}
