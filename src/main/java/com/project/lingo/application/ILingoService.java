package com.project.lingo.application;

import com.project.lingo.domain.Lingo;
import com.project.lingo.domain.LingoMet2Spelers;
import com.project.lingo.domain.Game;
import com.project.lingo.domain.User;

public interface ILingoService {
    Lingo start(int lengthWord, User user);
    String spelerSpeelt(String woordVanSpeler);
    Game nieuwSpel();
    Game nieuwSpelMetGebruiker(User user);
    Game nieuwePoging(String woordVanSpeler, long gameId);
    LingoMet2Spelers gameMet2Spelers(String naamSpeler1, String naamSpeler2, int tijdPerBeurt);
    String nieuwWoord();
}
