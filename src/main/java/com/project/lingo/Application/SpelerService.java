package com.project.lingo.Application;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;
import com.project.lingo.Presentation.dto.SpelerDto;
import com.project.lingo.Presentation.error.SpelerAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpelerService implements ISpelerService{
    private SpelerRepository repository;
    private SpelRepository spelRepository;
    private PasswordEncoder passwordEncoder;

    public SpelerService(SpelerRepository repository){
        this.repository = repository;
    }

    @Override
    public Speler registreerNieuwAccount(SpelerDto accountDto){
        if (emailBestaat(accountDto.getEmail())) {
            throw new SpelerAlreadyExistException("Er is al een account met dit emailadress");
        }

        final Speler speler = new Speler();

        speler.setGebruikersnaam(accountDto.getGebruikersnaam());
        speler.setRol(accountDto.getRole());
        speler.setWachtwoord(passwordEncoder.encode(accountDto.getWachtwoord()));
        speler.setEmail(accountDto.getEmail());
        return repository.save(speler);
    }

    private boolean emailBestaat(String email) {
        return repository.findSpelerByEmail(email) != null;
    }

    @Override
    public Speler vindSpelerBijEmail(String email){
        return repository.findSpelerByEmail(email);
    }

    @Override
    public List<Spel> vindSpellenBySpelerGebruikersnaam(String gebruikersnaam) {
        return spelRepository.vindSpellenVoorSpelerByGebruikersnaam(gebruikersnaam);
    }

    @Override
    public List<Speler> vindAlle() {
        return (List<Speler>) repository.findAll();
    }


}
