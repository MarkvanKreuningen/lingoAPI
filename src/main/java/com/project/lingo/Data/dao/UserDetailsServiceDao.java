package com.project.lingo.Data.dao;
import java.util.HashSet;
import java.util.Set;

import com.project.lingo.Data.repository.SpelerRepository;
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
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException {
        Speler speler = spelerRepository.findByGebruikersnaam(gebruikersnaam);

        Set < GrantedAuthority > grantedAuthorities = new HashSet < > ();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails.User(speler.getGebruikersnaam(), speler.getWachtwoord(),
                grantedAuthorities);
    }
}
