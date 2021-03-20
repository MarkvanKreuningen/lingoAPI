package com.project.lingo.Application;

import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Speler;
import com.project.lingo.Presentation.dto.SpelerDto;
import com.project.lingo.Presentation.error.SpelerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class SpelerService {
    @Autowired
    private SpelerRepository spelerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Speler registreerNieuwAccount(final SpelerDto accountDto) {
        if (emailBestaat(accountDto.getEmail())) {
            throw new SpelerAlreadyExistException("Er is al een account met dit emailadress: " + accountDto.getEmail());
        }
        final Speler speler = new Speler();

        speler.setGebruikersnaam(accountDto.getGebruikersnaam());
        speler.setRol(accountDto.getRole());
        speler.setWachtwoord(passwordEncoder.encode(accountDto.getWachtwoord()));
        speler.setEmail(accountDto.getEmail());
        return spelerRepository.save(speler);
    }

    private boolean emailBestaat(final String email) {
        return spelerRepository.findByEmail(email) != null;
    }

    public Speler vindSpelerBijEmail(final String email){
        return spelerRepository.findByEmail(email);
    }


}
