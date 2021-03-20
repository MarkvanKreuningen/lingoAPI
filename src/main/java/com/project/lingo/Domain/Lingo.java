package com.project.lingo.Domain;

import com.project.lingo.Application.FilterFileService;
import com.project.lingo.Application.ServiceProvider;

import java.util.*;

public class Lingo {
    //Nog te doen!
    //Timer fixen
    //

    FilterFileService filterFileService = ServiceProvider.getFilterFileService();
    private final ArrayList<String> list = filterFileService.getFilteredList();
    private int beurt;
    private String woordVanSpeler;
    private String teRadenWoord;
    private long startTijd;
    private Spel spel;

    public void setBeurt() {
        this.beurt += 1;
    }
    public void setSpel(Spel spel){
        this.spel = spel;
    }

    public String start() {
        this.beurt = 0;
        this.startTijd = System.nanoTime();
        return "Het woord van " +getTeRadenWoord().length() + " letters begint met een " + getTeRadenWoord().charAt(0);
    }

    public String spelerSpeelt(int aantalSeconde) {
        System.out.println(teRadenWoord);
        if (durationTeLang(aantalSeconde))
            return spelerIsAf(getFeedback());
         else if (teRadenWoord.equals(woordVanSpeler))
            return woordIsGeraden();
        else if (this.beurt >= 5)
            return spelerIsAf(getFeedback());
        else
            return getFeedback();
    }

    public boolean durationTeLang(int keer){
        long eindTijd = System.nanoTime();
        long durationInMilliSeconde = (eindTijd - startTijd) / 1000000;
        return durationInMilliSeconde >= (keer * 1000L);
    }

    public void setWoordVanSpeler(String woordVanSpeler) {
        this.woordVanSpeler = woordVanSpeler;
    }

    public String getFeedback() {
        if (!teRadenWoord.equals("") && !woordVanSpeler.equals("")) {
            if (woordVanSpeler.length() != teRadenWoord.length())
                return String.format("%s (ongeldig)", woordVanSpeler);
            else
                return new Poging(this.beurt, this.teRadenWoord, this.woordVanSpeler).toStringZonderTeRadenWoord();
        } else {
            return "Niks ingevuld";
        }
    }


    public String getTeRadenWoord() {
        return teRadenWoord;
    }

    public void setTeRadenWoord(int lengte) {
        Random random = new Random();
        this.teRadenWoord = "";
        while (true) {
            if (teRadenWoord.length() != lengte)
                teRadenWoord = list.get(random.nextInt(list.size()));
            else
                break;
        }
    }

    public String woordIsGeraden() {
        this.spel.setTotaalPunten(spel.getTotaalPunten() + teRadenWoord.length());
        if (teRadenWoord.length() == 7)
            setTeRadenWoord(5);
        else setTeRadenWoord(teRadenWoord.length() + 1);
        return "U heeft het woord geraden!\n" + start();
    }

    public String spelerIsAf(String laatsteFeedback) {
        return laatsteFeedback + "\nHelaas u heeft het woord niet geraden.\nU eindigt met een score van " + this.spel.getTotaalPunten();
    }

}
