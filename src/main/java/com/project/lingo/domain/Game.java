package com.project.lingo.domain;

import com.project.lingo.application.IAttemptService;
import com.project.lingo.application.IFilterFileService;
import com.project.lingo.application.IGameService;
import com.project.lingo.presentation.dto.AttemptDto;
import com.project.lingo.presentation.dto.WordDto;
import com.project.lingo.presentation.error.*;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private long id;

    @Column(name = "totaalpunten")
    private int totalPoints;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "datum")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JsonBackReference
    @JoinColumn(name = "speler_fk", referencedColumnName = "id")
    @NotNull
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "spel_fk", referencedColumnName = "id")
    List<Attempt> attempts = new ArrayList<>();

    public Game(User user){
        this.user = user;
        this.totalPoints = 0;
        this.status = Status.NEW;
        this.date = new Date();
    }

    public Game() {

    }

    public long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totaalPunten) {
        this.totalPoints = totaalPunten;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date datum) {
        this.date = datum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> pogingen) {
        this.attempts = pogingen;
    }

    public Object userPlays(Attempt attempt, IGameService gameService, String word, IAttemptService attemptService, IFilterFileService filterFileService) throws WordNotValid, GameOverException {
        AttemptDto attemptDto = attemptService.getFeedback(attempt, word, this);
        if (attempt.getTeRadenWoord().equals(word)){
            WordDto wordDto = this.nextRound(filterFileService, attemptService, this.getNextWordLength(attempt.getTeRadenWoord()), attempt.getRound());
            gameService.post(this);
            return wordDto;
        }
        return attemptDto;
    }

    //TODO!!
    //na 5 beurten gameover
    //na een te laat beurt wel kunnen spelen maar 1 attempt extra posten naar repository
    //attempt lastattempt = getlastattempt(); post(lastattempt);
    public void legalAttempt(Attempt lastAttempt, IGameService gameService) throws GameOverException, TooLateException {
        if (this.getStatus() == Status.GAMEOVER)
            throw new GameOverException("Game is already over");
        else if (gameOutdated(lastAttempt.getCreated().getTime())){
            this.setStatus(Status.GAMEOVER);
            gameService.post(this);
            throw new TooLateException("This game is the same age as your milk in the refrigerator");
        } else if (durationTooLong(lastAttempt.getCreated().getTime())){
            this.setStatus(Status.GAMEOVER);
            gameService.post(this);
            throw new TooLateException("Your answer is too late!");
        }
    }

    public int getNextWordLength(String word){
        if (word.length() == 7)
            return 5;
        else return word.length() + 1;
    }

    //TODO!!
    public WordDto nextRound(IFilterFileService filterFileService, IAttemptService attemptService, int lengthWord, int round) throws GameOverException {
        if (this.getStatus() == Status.GAMEOVER){
            throw new GameOverException("Game is already over");
        }
        this.totalPoints += 25;
        round += 1;
        System.out.println(this.toString() + "\n" + lengthWord);
        Attempt attempt = attemptService.newAttempt(this, lengthWord, filterFileService, round);
        return attempt.getWordDto();
    }

    //TODO!!
    public void newAttemptForEveryXMilliSeconds(IAttemptService attemptService, int lengthWord, IFilterFileService filterFileService){
        for (int i = 150000; i > 60000; i =- 60000){
            attemptService.newAttempt(this, lengthWord, filterFileService, 0);
        }
    }


    public WordDto start(IFilterFileService filterFileService, IAttemptService attemptService, IGameService gameService) throws GameOverException, StartedException {
        if (this.getStatus() == Status.GAMEOVER) {
            throw new GameOverException("Game is already over");
        } else if (this.getStatus() == Status.START) {
            throw new StartedException("Game already started");
        }
        this.setStatus(Status.START);
        WordDto wordDto = attemptService.newAttempt(this, 5, filterFileService, 1).getWordDto();
        gameService.post(this);
        return wordDto;
    }

    //TODO!!
    public boolean durationTooLong(long startTime){
        long endTime = new Timestamp(System.currentTimeMillis()).getTime();
        long durationInMilliSeconds = endTime - startTime;
        return durationInMilliSeconds > 60000;
    }

    public boolean gameOutdated(long startTime){
        long endTime = new Timestamp(System.currentTimeMillis()).getTime();
        long durationInMinutes = (endTime - startTime) / 60000;
        return durationInMinutes > 10;
    }
}
