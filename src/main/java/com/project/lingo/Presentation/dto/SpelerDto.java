package com.project.lingo.Presentation.dto;

import com.project.lingo.validation.ValidEmail;
import com.project.lingo.validation.ValidWachtwoord;
import com.project.lingo.validation.WachtwoordMatches;
import com.sun.istack.NotNull;
import jakarta.validation.constraints.Size;

@WachtwoordMatches
public class SpelerDto {
    @NotNull
    @Size(min = 1, message = "{Size.spelerDto.gebruikersnaam}")
    public String gebruikersnaam;

    @ValidWachtwoord
    private String wachtwoord;

    @NotNull
    @Size(min = 1)
    private String matchingWachtwoord;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.spelerDto.email}")
    private String email;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(final String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(final String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getMatchingWachtwoord() {
        return matchingWachtwoord;
    }

    public void setMatchingWachtwoord(final String matchingWachtwoord) {
        this.matchingWachtwoord = matchingWachtwoord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("SpelerDto [gebruikersnaam=")
                .append(gebruikersnaam)
                .append(", email=")
                .append(email)
                .append(", role=")
                .append(role).append("]");
        return builder.toString();
    }
}
