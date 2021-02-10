package com.project.lingo.Domain;

import com.project.lingo.Application.FilterFileService;
import com.project.lingo.Application.ServiceProvider;

import java.text.SimpleDateFormat;
import java.util.*;

public class Lingo {
    FilterFileService filterFileService = ServiceProvider.getFilterFileService();
    private ArrayList<String> list = filterFileService.getFilteredList();
    private int score;
    private String feedback;
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
                this.feedback = String.format("%s (ongeldig)", woordVanSpeler);
            else
                this.feedback = feedbackPerCharacter();

        } else {
            this.feedback = "Niks ingevuld";
        }
        return feedback;
    }

    public String feedbackPerCharacter() {
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
            for (int j = 0; j < teRadenWoord.length(); j++) {
                if (geradenWoord[i] == teRaden[j] && feedback[i] == Feedback.ABSENT) {
                    feedback[i] = Feedback.PRESENT;
                    teRaden[j] = '!';
                }
            }
        }
        System.out.println(beurt + " : " + teRadenWoord + " : " + woordVanSpeler + "\n" + Arrays.toString(feedback));
        return Arrays.toString(feedback);
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
