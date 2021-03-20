package com.project.lingo.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "poging")
public class Poging {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private int beurt;
    @Column(name = "woordvanspeler")
    private String woordVanSpeler;
    @Column
    private String feedback;
    @Column(name = "teradenwoord")
    private String teRadenWoord;

    public Poging(int beurt, String teRadenWoord, String woordVanSpeler) {
        this.beurt = beurt;
        this.woordVanSpeler = woordVanSpeler;
        this.teRadenWoord = teRadenWoord;
        Feedback[] feedback = new Feedback[teRadenWoord.length()];
        Arrays.fill(feedback, Feedback.ABSENT);
        char[] teRaden = teRadenWoord.toCharArray();
        char[] geradenWoord = woordVanSpeler.toCharArray();
        for (int i = 0; i < teRadenWoord.length(); i++) {
            if (geradenWoord[i] == teRaden[i]) {
                feedback[i] = Feedback.CORRECT;
                teRaden[i] = '!';
            }
        }
        for (int i = 0; i < teRadenWoord.length(); i++) {
            for (int x = 0; x < teRadenWoord.length(); x++) {
                if (geradenWoord[i] == teRaden[x] && feedback[i] == Feedback.ABSENT) {
                    feedback[i] = Feedback.PRESENT;
                    teRaden[x] = '!';
                }
            }
        }
        this.feedback = Arrays.toString(feedback);
        System.out.println(this.feedback);
    }

    public Poging() {

    }

    public int getBeurt() {
        return beurt;
    }

    public void setBeurt(int beurt) {
        this.beurt = beurt;
    }

    public String getWoordVanSpeler() {
        return woordVanSpeler;
    }

    public void setWoordVanSpeler(String woordVanSpeler) {
        this.woordVanSpeler = woordVanSpeler;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTeRadenWoord() {
        return teRadenWoord;
    }

    public void setTeRadenWoord(String teRadenWoord) {
        this.teRadenWoord = teRadenWoord;
    }

    @Override
    public String toString() {
        return "Poging{" +
                "beurt=" + beurt +
                ", woordVanSpeler='" + woordVanSpeler + '\'' +
                ", feedback='" + feedback + '\'' +
                ", teRadenWoord='" + teRadenWoord + '\'' +
                '}';
    }

    public String toStringZonderTeRadenWoord(){
        return "Poging{" +
                "beurt=" + beurt +
                ", woordVanSpeler='" + woordVanSpeler + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
