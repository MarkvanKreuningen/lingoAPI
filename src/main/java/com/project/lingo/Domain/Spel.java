package com.project.lingo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @NotNull
    private Speler speler;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "spel_fk", referencedColumnName = "id")
    List<Poging> pogingen = new ArrayList<>();

    public Spel(Speler speler){
        this.speler = speler;
        this.totaalPunten = 0;
        this.datum = new Date();
    }

    public Spel() {

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

    public List<Poging> getPogingen() {
        return pogingen;
    }

    public void setPogingen(List<Poging> pogingen) {
        this.pogingen = pogingen;
    }

    @Override
    public String toString() {
        return "Spel{" +
                "id=" + id +
                ", totaalPunten=" + totaalPunten +
                ", datum=" + datum +
                ", pogingen=" + pogingen +
                '}';
    }
}
