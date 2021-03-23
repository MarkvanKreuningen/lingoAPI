package com.project.lingo.Application;

import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;

import java.util.List;
import java.util.Optional;

public interface ISpelService {
    List<Spel> vindAlle();
    Spel vindById(long id);
    Spel start();
    Spel nieuwSpel(Speler speler);
}
