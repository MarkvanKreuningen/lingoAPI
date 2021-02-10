package com.project.lingo.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Speler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @Column
    private String gebruikersnaam;

    @Column
    private String wachtwoord;

    public Speler(String email, String gebruikersnaam, String wachtwoord) {
        this.email = email;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }
    public Speler(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }


}
