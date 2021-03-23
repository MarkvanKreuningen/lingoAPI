package com.project.lingo.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "speler")
public class Speler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @NotNull
    private String gebruikersnaam;

    @Column
    @NotNull
    private String wachtwoord;

    @NotNull
    private String rol;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "speler_fk", referencedColumnName = "id")
    @JsonManagedReference
    private Collection<Spel> spellen;

    public Speler(long id, String email, String gebruikersnaam, String wachtwoord, Collection<Spel> spellen) {
        this.id = id;
        this.email = email;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.spellen = spellen;
    }

    public Speler(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Collection<Spel> getSpellen() {
        return spellen;
    }

    public void setSpellen(Collection<Spel> spellen) {
        this.spellen = spellen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Speler{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", rol='" + rol + '\'' +
                ", spellen=" + spellen +
                '}';
    }
}
