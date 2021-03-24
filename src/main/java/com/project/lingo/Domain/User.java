package com.project.lingo.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "speler")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true, name = "gebruikersnaam")
    @NotNull
    private String username;

    @Column(name = "wachtwoord")
    @NotNull
    private String password;

    @NotNull
    private String rol;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "speler_fk", referencedColumnName = "id")
    @JsonManagedReference
    private Collection<Game> spellen;

    /*private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }*/

    public User(long id, String email, String username, String password, Collection<Game> spellen) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.spellen = spellen;
    }

    public User(){

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String wachtwoord) {
        this.password = wachtwoord;
    }

    public Collection<Game> getSpellen() {
        return spellen;
    }

    public void setSpellen(Collection<Game> spellen) {
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
                ", gebruikersnaam='" + username + '\'' +
                ", wachtwoord='" + password + '\'' +
                ", rol='" + rol + '\'' +
                ", spellen=" + spellen +
                '}';
    }
}
