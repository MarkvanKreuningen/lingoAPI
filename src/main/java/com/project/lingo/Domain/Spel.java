package com.project.lingo.Domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spel")
public class Spel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private int totaalPunten;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    private Date datum;

    @ManyToOne()
    @JoinColumn(name = "speler_fk", referencedColumnName = "id")
    private Speler speler;

    public int getTotaalPunten() {
        return totaalPunten;
    }

    public long getId() {
        return id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setTotaalPunten(int totaalPunten) {
        this.totaalPunten = totaalPunten;
    }

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
}
