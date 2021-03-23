package com.project.lingo.Application;

import com.project.lingo.Domain.Lingo;
import com.project.lingo.Domain.LingoMet2Spelers;
import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;

public interface ILingoService {
    Lingo start(int lengthWord, Speler speler);
    String spelerSpeelt(String woordVanSpeler);
    Spel nieuwSpel();
    Spel nieuwSpelMetGebruiker(Speler speler);
    Spel nieuwePoging(String woordVanSpeler, long gameId);
    LingoMet2Spelers gameMet2Spelers(String naamSpeler1, String naamSpeler2, int tijdPerBeurt);
    String nieuwWoord();
}
