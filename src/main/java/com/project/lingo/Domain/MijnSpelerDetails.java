package com.project.lingo.Domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MijnSpelerDetails implements UserDetails {
    private Speler speler;

    public MijnSpelerDetails(Speler speler) {
        this.speler = speler;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(speler.getRol());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return speler.getWachtwoord();
    }

    @Override
    public String getUsername() {
        return speler.getGebruikersnaam();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
