package com.project.lingo.Domain;

import com.project.lingo.Application.IAttemptService;
import com.project.lingo.Application.IFilterFileService;
import com.project.lingo.Application.IGameService;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.GameOverException;
import com.project.lingo.Presentation.error.NewGameException;
import com.project.lingo.Presentation.error.StartedException;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

    public boolean attempt() throws GameOverException, NewGameException {
        if (this.getStatus() == Status.GAMEOVER){
            throw new GameOverException("Game is already over");
        } else if (this.getStatus() == Status.NEW){
            throw new NewGameException("Game not started yet!");
        }
        //else if (durationTooLong())
        return false;

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
        Attempt attempt = new Attempt(this, lengthWord, filterFileService, attemptService);
        return attempt.getWordDto();
    }


    public boolean durationTooLong(int startTime){
        long endTime = System.nanoTime();
        long durationInMilliSeconds = (endTime - startTime) / 1000000;
        return durationInMilliSeconds >= (60 * 1000L);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", totalPoints=" + totalPoints +
                ", date=" + date +
                ", username=" + user.getUsername() +
                ", attempts=" + attempts +
                '}';
    }

    public String toStringWithoutUser() {
        return "Game{" +
                "id=" + id +
                ", totalPoints=" + totalPoints +
                ", date=" + date +
                ", attempts=" + attempts +
                '}';
    }
}
