package com.project.lingo.domain;

import com.project.wordGenerator.application.FilterFileService;
import com.project.wordGenerator.application.IFilterFileService;

import java.util.List;
import java.util.Random;

public class LingoMet2Spelers {
    IFilterFileService filterFileService = new FilterFileService();
    final List<String> list = filterFileService.getFilteredList();
    private int tijdPerBeurt;
    private SpelMet2Spelers spel;
    String teRadenWoord;
    private Beurt beurt;
    private String naamSpeler1;
    private String naamSpeler2;
    private int totaalPuntenSpeler1;
    private int totaalPuntenSpeler2;

    public LingoMet2Spelers(String naamSpeler1, String naamSpeler2, int tijdPerBeurt){
        this.spel = new SpelMet2Spelers(naamSpeler1, naamSpeler2);
        this.tijdPerBeurt = tijdPerBeurt;
        spel.setBeurt(randomBeurt());
        this.beurt = spel.getBeurt();
        this.naamSpeler1 = naamSpeler1;
        this.naamSpeler2 = naamSpeler2;
        this.totaalPuntenSpeler1 = 0;
        this.totaalPuntenSpeler2 = 0;
    }

    public LingoMet2Spelers(){

    }

    public SpelMet2Spelers start(String naamSpeler1, String naamSpeler2, int tijdPerBeurt){
        this.spel = new SpelMet2Spelers(naamSpeler1, naamSpeler2);
        this.tijdPerBeurt = tijdPerBeurt;
        spel.setBeurt(randomBeurt());
        return spel;
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


    public Beurt randomBeurt(){
        int pick = new Random().nextInt(Beurt.values().length);
        return Beurt.values()[pick];
    }


    @Override
    public String toString() {
        return "LingoMet2Spelers{" +
                "tijdPerBeurt=" + tijdPerBeurt +
                ", teRadenWoord='" + teRadenWoord + '\'' +
                ", beurt=" + beurt +
                ", naamSpeler1='" + naamSpeler1 + '\'' +
                ", naamSpeler2='" + naamSpeler2 + '\'' +
                ", totaalPuntenSpeler1=" + totaalPuntenSpeler1 +
                ", totaalPuntenSpeler2=" + totaalPuntenSpeler2 +
                '}';
    }


}
