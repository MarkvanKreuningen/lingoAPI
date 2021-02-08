package com.project.lingo.Domain;

import com.project.lingo.Application.FilterFileService;
import com.project.lingo.Application.ServiceProvider;

import java.util.*;

public class Lingo {
    FilterFileService filterFileService = ServiceProvider.getFilterFileService();
    private ArrayList<String> list = filterFileService.getFilteredList();
    private String gegeven;
    private int score;
    private String name;
    private String feedback;
    private int beurt;
    private String raadBeurt;
    private String teRadenWoord;
    private boolean woordGeraden;

    public void setWoordGeraden(boolean woordGeraden) {
        this.woordGeraden = woordGeraden;
    }

    public int getBeurt() {
        return beurt;
    }

    public void setBeurt() {
        this.beurt = beurt + 1;
    }

    public void setRaadBeurt(String raadBeurt) {
        this.raadBeurt = raadBeurt;
    }

    public String getFeedback() {
        feedback = "geen";
        if (teRadenWoord.equals(raadBeurt)) {
            feedback = "Je hebt het woord geraden gefeliciteerd!";
            setWoordGeraden(true);
        } else {
            if (!teRadenWoord.equals("") && !raadBeurt.equals("")) {
                if (raadBeurt.length() != teRadenWoord.length()) {
                    feedback = String.format("%s (ongeldig)", raadBeurt);
                } else {
                    feedback = feedbackPerCharacter();
                }
            } else {
                feedback = "Niks ingevuld";
            }
        }
        return feedback;
    }

    public String feedbackPerCharacter() {
        Feedback[] feedback = new Feedback[teRadenWoord.length()];
        Arrays.fill(feedback, Feedback.ABSENT);
        char[] teRaden = teRadenWoord.toCharArray();
        char[] geradenWoord = raadBeurt.toCharArray();
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
        System.out.println(teRadenWoord + ":" + raadBeurt + "\n" + Arrays.toString(feedback));
        return Arrays.toString(feedback);
    }


    public String getTeRadenWoord() {
        return teRadenWoord;
    }

    public void setTeRadenWoord(int lengte) {
        Random random = new Random();
        this.teRadenWoord = "";
        while (true) {
            if (teRadenWoord.length() != lengte) {
                teRadenWoord = list.get(random.nextInt(list.size()));
            } else {
                break;
            }
        }
    }

}
