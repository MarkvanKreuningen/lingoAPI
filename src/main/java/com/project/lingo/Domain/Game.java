package com.project.lingo.Domain;

import com.project.lingo.Application.GameService;
import com.project.lingo.Application.IAttemptService;
import com.project.lingo.Application.IFilterFileService;
import com.project.lingo.Application.IGameService;
import com.project.lingo.Presentation.dto.AttemptDto;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.*;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.parameters.P;

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

    public Object userPlays(Attempt attempt, IGameService gameService, String word, IAttemptService attemptService, IFilterFileService filterFileService) throws GameOverException, TooLateException, WordNotValid, StartedException {
        if (this.getStatus() == Status.GAMEOVER)
            throw new GameOverException("Game is already over");
        else if (durationTooLong(attempt.getCreated().getTime())){
            this.setStatus(Status.GAMEOVER);
            throw new TooLateException("Your answer is too late!");
        }
        AttemptDto attemptDto = attemptService.getFeedback(attempt, word, this);
        if (attempt.getTeRadenWoord().equals(word)){
            WordDto wordDto = this.nextRound(filterFileService, attemptService, this.getNextWordtLength(attempt));
            gameService.post(this);
            return wordDto;
        }
        return attemptDto;
    }

    public int getNextWordtLength(Attempt attempt){
        if (attempt.getTeRadenWoord().length() == 7)
            return 5;
        else return attempt.getTeRadenWoord().length() + 1;
    }

    public WordDto nextRound(IFilterFileService filterFileService, IAttemptService attemptService, int lengthWord) throws GameOverException {
        if (this.getStatus() == Status.GAMEOVER){
            throw new GameOverException("Game is already over");
        }
        this.totalPoints += 25;
        Attempt attempt = new Attempt(this, lengthWord, filterFileService, attemptService);
        return attempt.getWordDto();
    }


    public WordDto start(IFilterFileService filterFileService, IAttemptService attemptService, IGameService gameService) throws GameOverException, StartedException {
        WordDto word = this.nextAttempt(filterFileService, attemptService, 5);
        gameService.post(this);
        return word;
    }

    public WordDto nextAttempt(IFilterFileService filterFileService, IAttemptService attemptService, int lengthWord) throws GameOverException, StartedException {
        if (this.getStatus() == Status.GAMEOVER) {
            throw new GameOverException("Game is already over");
        } else if (this.getStatus() == Status.START) {
            throw new StartedException("Game already started");
        }
        this.setStatus(Status.START);
        Attempt attempt = attemptService.newAttempt(this, lengthWord, filterFileService, attemptService);
        return attempt.getWordDto();
    }


    public boolean durationTooLong(long startTime){
        long endTime = new Timestamp(System.currentTimeMillis()).getTime();
        long durationInMilliSeconds = (endTime - startTime) / 1000000;
        return durationInMilliSeconds >= (60 * 1000L);
    }
}
