package com.project.lingo.Domain;

import com.project.lingo.Application.IAttemptService;
import com.project.lingo.Application.IFilterFileService;
import com.project.lingo.Presentation.dto.AttemptDto;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.WordNotValid;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column(name = "beurt")
    private int turn;
    @Column(name = "woordvanspeler")
    private String woordVanSpeler;
    @Column
    private String feedback;
    @Column(name = "teradenwoord")
    private String teRadenWoord;

    private Timestamp created;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonBackReference
    @JoinColumn(name = "spel_fk", referencedColumnName = "id")
    @NotNull
    private Game game;

    public Attempt(Game game, int size, IFilterFileService filterFileService, IAttemptService attemptService){
        this.game = game;
        this.setTeRadenWoord(size, filterFileService.getFilteredList());
        this.teRadenWoord = this.getTeRadenWoord();
        this.turn = attemptService.getTotalTurns(this.teRadenWoord, game.getId());
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Attempt(int turn, String teRadenWoord, String woordVanSpeler, Game game) {
        this.game = game;
        this.turn = turn;
        this.woordVanSpeler = woordVanSpeler;
        this.teRadenWoord = teRadenWoord;
        this.created = new Timestamp(System.currentTimeMillis());
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
    public WordDto getWordDto(){
        return new WordDto(this.game.getId(), this.getTeRadenWoord().charAt(0), this.getTeRadenWoord().length());
    }

    public AttemptDto getAttemptDto(){
        return new AttemptDto(this.turn, this.game.getId(), this.feedback, this.created);
    }

    public Attempt() {

    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int beurt) {
        this.turn = beurt;
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

    public void setTeRadenWoord(int lengte, List<String> list) {
        Random random = new Random();
        this.teRadenWoord = "";
        while (true) {
            if (teRadenWoord.length() != lengte)
                teRadenWoord = list.get(random.nextInt(list.size()));
            else
                break;
        }
    }

    public Attempt rateAttempt(Attempt attempt, String word, Game game) throws WordNotValid {
        if (!word.equals("")){
            if (word.length() != attempt.getTeRadenWoord().length())
            throw new WordNotValid("Word empty/not the same length");
        }
        System.out.println("Attempt: rateAttempt: 131 "+new Attempt(attempt.getTurn() + 1, attempt.getTeRadenWoord(), word, game).toString());
        return new Attempt(attempt.getTurn() + 1, attempt.getTeRadenWoord(), word, game);
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", turn=" + turn +
                ", woordVanSpeler='" + woordVanSpeler + '\'' +
                ", feedback='" + feedback + '\'' +
                ", teRadenWoord='" + teRadenWoord + '\'' +
                ", created=" + created +
                ", game=" + game +
                '}';
    }

    public String toStringZonderTeRadenWoord(){
        return "Attempt{" +
                "turn=" + turn +
                ", woordVanSpeler='" + woordVanSpeler + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
