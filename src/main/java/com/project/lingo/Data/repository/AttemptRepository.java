package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Attempt;
import com.project.lingo.Domain.Game;
import com.project.lingo.Presentation.error.NoAttemptsFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    @Query(value = "select count(a) from Attempt a where a.teRadenWoord = ?1 and a.id = ?2")
    int getTotalTurns(String word, long id);
    @Query(value = "select a from Attempt a, Game g where a.game.id = g.id and g.id = ?1 order by a.created desc")
    List<Attempt> getLastAttemptByGame(long gameId) throws NoAttemptsFoundException;

}
