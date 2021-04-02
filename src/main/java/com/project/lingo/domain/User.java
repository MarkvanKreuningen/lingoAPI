package com.project.lingo.domain;

import com.sun.istack.NotNull;


import javax.persistence.*;

@Entity
@Table(name = "useraccount")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @NotNull
    private String username;

    @Column
    @NotNull
    private String password;

    @NotNull
    private String rol;

    public User(long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
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
                '}';
    }
}
