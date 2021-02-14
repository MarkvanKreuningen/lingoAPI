package com.project.lingo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spel")
public class Spel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private long id;

    @Column(name = "totaalpunten")
    private int totaalPunten;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "datum")
    private Date datum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "speler_fk", referencedColumnName = "id")
    private Speler speler;

    public Spel(long id, int totaalPunten, Date datum, Speler speler) {
        this.id = id;
        this.totaalPunten = totaalPunten;
        this.datum = datum;
        this.speler = speler;
    }

    public Spel(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotaalPunten() {
        return totaalPunten;
    }

    public void setTotaalPunten(int totaalPunten) {
        this.totaalPunten = totaalPunten;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
}
