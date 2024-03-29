package com.project.lingo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.lingo.application.IAttemptService;
import com.project.lingo.presentation.dto.AttemptDto;
import com.project.lingo.presentation.dto.WordDto;
import com.project.lingo.presentation.error.WordNotValidException;
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
    private int round;
    @Column(name = "woordvanspeler")
    private String woordVanSpeler;
    @Column
    private String feedback;
    @Column(name = "teradenwoord")
    private String teRadenWoord;

    private Timestamp created;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "spel_fk", referencedColumnName = "id")
    @NotNull
    private Game game;

    public Attempt(int turn, String teRadenWoord, String woordVanSpeler, Game game, int round) {
        this.game = game;
        this.turn = turn;
        this.woordVanSpeler = woordVanSpeler;
        this.teRadenWoord = teRadenWoord;
        this.created = new Timestamp(System.currentTimeMillis());
        this.feedback = createFeedback();
        this.round = round;
    }

    public Attempt(int turn, String teRadenWoord, Game game, int round){
        this.game = game;
        this.turn = turn;
        this.teRadenWoord = teRadenWoord;
        this.created = new Timestamp(System.currentTimeMillis());
        this.round = round;
    }

    public WordDto getWordDto(){
        return new WordDto(this.game.getId(), this.getTeRadenWoord().charAt(0), this.getTeRadenWoord().length(), this.round);
    }

    public AttemptDto getAttemptDto(){
        return new AttemptDto(this.turn, this.game.getId(), this.feedback, this.created, this.round);
    }

    public Attempt() {

    }

    public int getTurn() {
        return turn;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
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

    public void setTeRadenWoord(String teRadenWoord) {
        this.teRadenWoord = teRadenWoord;
    }

    public String createFeedback(){
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
        return Arrays.toString(feedback);
    }

    public Attempt buildAttempt(Attempt attempt, String word, Game game) {
        Attempt returnAttempt = new Attempt(attempt.getTurn() + 1, attempt.getTeRadenWoord(), word, game, attempt.getRound());
        System.out.println(returnAttempt.toString());
        return returnAttempt;
    }

    public void checkLegalAttempt(Attempt attempt, String word, Game game, IAttemptService attemptService) throws WordNotValidException {
        if (word.equals("")){
            Attempt illegalAttempt = new Attempt(attempt.getTurn() + 1, attempt.getTeRadenWoord(), game, attempt.getRound());
            attemptService.post(illegalAttempt);
            throw new WordNotValidException("Word is empty!");
        } else if (word.length() != attempt.getTeRadenWoord().length()){
            saveIllegalAttempt(attempt, word, game, attemptService);
            throw new WordNotValidException("Word is not the same lenght as the word to guess");
        } else if (!attemptService.validateWord(word)){
            saveIllegalAttempt(attempt, word, game, attemptService);
            throw new WordNotValidException("Word does not exist!");
        }
    }

    public void saveIllegalAttempt(Attempt attempt, String word, Game game, IAttemptService attemptService){
        Attempt illegalAttempt = new Attempt(attempt.getTurn() + 1, attempt.getTeRadenWoord(), word, game, attempt.getRound());
        attemptService.post(illegalAttempt);
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
                ", round=" + round +
                ", woordVanSpeler='" + woordVanSpeler + '\'' +
                ", feedback='" + feedback + '\'' +
                ", teRadenWoord='" + teRadenWoord + '\'' +
                ", created=" + created +
                '}';
    }
}
