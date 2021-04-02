package com.project.lingo.domain;

import java.util.Date;
import java.util.List;

public class SpelMet2Spelers {
    private long id;
    private String naamSpeler1;
    private String naamSpeler2;
    private int totaalPuntenSpeler1;
    private int totaalPuntenSpeler2;
    private Date date;
    private Beurt beurt;
    List<Attempt> pogingenSpeler1;
    List<Attempt> pogingenSpeler2;

    public SpelMet2Spelers(String naamSpeler1, String naamSpeler2){
        this.id = 1;
        this.naamSpeler1 = naamSpeler1;
        this.naamSpeler1 = naamSpeler2;
        this.date = new Date();
        this.totaalPuntenSpeler1 = 0;
        this.totaalPuntenSpeler2 = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaamSpeler1() {
        return naamSpeler1;
    }

    public void setNaamSpeler1(String naamSpeler1) {
        this.naamSpeler1 = naamSpeler1;
    }

    public String getNaamSpeler2() {
        return naamSpeler2;
    }

    public void setNaamSpeler2(String naamSpeler2) {
        this.naamSpeler2 = naamSpeler2;
    }

    public int getTotaalPuntenSpeler1() {
        return totaalPuntenSpeler1;
    }

    public void setTotaalPuntenSpeler1(int totaalPuntenSpeler1) {
        this.totaalPuntenSpeler1 = totaalPuntenSpeler1;
    }

    public int getTotaalPuntenSpeler2() {
        return totaalPuntenSpeler2;
    }

    public void setTotaalPuntenSpeler2(int totaalPuntenSpeler2) {
        this.totaalPuntenSpeler2 = totaalPuntenSpeler2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Attempt> getPogingenSpeler1() {
        return pogingenSpeler1;
    }

    public void setPogingenSpeler1(List<Attempt> pogingenSpeler1) {
        this.pogingenSpeler1 = pogingenSpeler1;
    }

    public List<Attempt> getPogingenSpeler2() {
        return pogingenSpeler2;
    }

    public void setPogingenSpeler2(List<Attempt> pogingenSpeler2) {
        this.pogingenSpeler2 = pogingenSpeler2;
    }

    public Beurt getBeurt() {
        return beurt;
    }

    public void setBeurt(Beurt beurt) {
        this.beurt = beurt;
    }

    @Override
    public String toString() {
        return "SpelMet2Spelers{" +
                "id=" + id +
                ", naamSpeler1='" + naamSpeler1 + '\'' +
                ", naamSpeler2='" + naamSpeler2 + '\'' +
                ", totaalPuntenSpeler1=" + totaalPuntenSpeler1 +
                ", totaalPuntenSpeler2=" + totaalPuntenSpeler2 +
                ", date=" + date +
                ", beurt=" + String.valueOf(beurt) +
                ", pogingenSpeler1=" + pogingenSpeler1 +
                ", pogingenSpeler2=" + pogingenSpeler2 +
                '}';
    }
}
