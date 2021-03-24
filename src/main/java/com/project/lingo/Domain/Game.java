package com.project.lingo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "spel")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private long id;

    @Column(name = "totaalpunten")
    private int totalPoints;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "datum")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "speler_fk", referencedColumnName = "id")
    @NotNull
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "spel_fk", referencedColumnName = "id")
    List<Poging> attempts = new ArrayList<>();

    public Game(User user){
        this.user = user;
        this.totalPoints = 0;
        this.date = new Date();
    }

    public Game() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totaalPunten) {
        this.totalPoints = totaalPunten;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date datum) {
        this.date = datum;
    }

    public List<Poging> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Poging> pogingen) {
        this.attempts = pogingen;
    }

    @Override
    public String toString() {
        return "Spel{" +
                "id=" + id +
                ", totaalPunten=" + totalPoints +
                ", datum=" + date +
                ", pogingen=" + attempts +
                '}';
    }
}
