package com.project.lingo.Domain;

public class Player {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public void addScore(int nieuweScore) {
        this.score = score + nieuweScore;
    }
}
