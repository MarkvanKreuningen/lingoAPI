package com.project.lingo.Domain;

import com.project.lingo.Application.FilterFileService;
import com.project.lingo.Application.ServiceProvider;

import java.text.SimpleDateFormat;
import java.util.*;

public class Lingo {
    //Nog te doen!
    //Timer fixen
    //

    FilterFileService filterFileService = ServiceProvider.getFilterFileService();
    private final ArrayList<String> list = filterFileService.getFilteredList();
    private int score;
    private int beurt;
    private String woordVanSpeler;
    private String teRadenWoord;
    private String tijd;

    public void setBeurt() {
        this.beurt += 1;
    }

    public String start() {
        this.beurt = 0;
        this.tijd = new SimpleDateFormat("HH.mm.ss").format(new Date());
        return "Het woord van " +getTeRadenWoord().length() + " letters begint met een " + getTeRadenWoord().charAt(0);
    }

    public String spelerSpeelt() {
        if (teRadenWoord.equals(woordVanSpeler))
            return woordIsGeraden();
        else if (this.beurt >= 5)
            return spelerIsAf(getFeedback());
        else
            return getFeedback();

    }

    public void setWoordVanSpeler(String woordVanSpeler) {
        this.woordVanSpeler = woordVanSpeler;
    }

    public String getFeedback() {
        if (!teRadenWoord.equals("") && !woordVanSpeler.equals("")) {
            if (woordVanSpeler.length() != teRadenWoord.length())
                return String.format("%s (ongeldig)", woordVanSpeler);
            else
                return new Poging(this.beurt, this.teRadenWoord, this.woordVanSpeler).toString();
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
        this.score += teRadenWoord.length();
        if (teRadenWoord.length() == 7)
            setTeRadenWoord(5);
        else setTeRadenWoord(teRadenWoord.length() + 1);
        return "U heeft het woord geraden!\n" + start();
    }

    public String spelerIsAf(String laatsteFeedback) {
        return laatsteFeedback + "\nHelaas u heeft het woord niet geraden.\nU eindigt met een score van " + this.score;
    }

}
