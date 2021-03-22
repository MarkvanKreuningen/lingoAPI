package com.project.lingo.Application;

import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Speler;
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

    private boolean emailBestaat(final String email) {
        return spelerRepository.findByEmail(email) != null;
    }

    public Speler vindSpelerBijEmail(final String email){
        return spelerRepository.findByEmail(email);
    }


}
