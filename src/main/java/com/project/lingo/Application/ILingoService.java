package com.project.lingo.Application;

import com.project.lingo.Domain.Lingo;
import com.project.lingo.Domain.LingoMet2Spelers;
import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;

public interface ILingoService {
    Lingo start(int lengthWord, User user);
    String spelerSpeelt(String woordVanSpeler);
    Game nieuwSpel();
    Game nieuwSpelMetGebruiker(User user);
    Game nieuwePoging(String woordVanSpeler, long gameId);
    LingoMet2Spelers gameMet2Spelers(String naamSpeler1, String naamSpeler2, int tijdPerBeurt);
    String nieuwWoord();
}
