package com.project.lingo.Application;

import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;
import com.project.lingo.Presentation.dto.SpelerDto;

import java.util.Collection;
import java.util.List;

public interface ISpelerService {
    Speler registreerNieuwAccount(SpelerDto accountDto);
    Speler vindSpelerBijEmail(String email);
    List<Spel> vindSpellenBySpelerGebruikersnaam(String gebruikersnaam);
    List<Speler> vindAlle();
}
