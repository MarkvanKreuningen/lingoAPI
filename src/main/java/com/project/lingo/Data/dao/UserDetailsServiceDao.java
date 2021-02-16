package com.project.lingo.Data.dao;
import java.util.HashSet;
import java.util.Set;

import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.MijnSpelerDetails;
import com.project.lingo.Domain.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServiceDao implements UserDetailsService {

    @Autowired
    private SpelerRepository spelerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Speler speler = spelerRepository.findByGebruikersnaam(username);

        if (speler == null) {
            throw new UsernameNotFoundException("Speler is niet gevonden");
        }

        return new MijnSpelerDetails(speler);
    }
}
