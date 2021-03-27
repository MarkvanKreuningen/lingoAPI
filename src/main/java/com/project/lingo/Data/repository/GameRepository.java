package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "select s from Game s, User r where r.username = ?1 and s.user.id = r.id")
    List<Game> findGamesForPlayerByUsername(String gebruikersnaam);
    @Query(value = "select g from Game g, User u where g.user.id = u.id and g.id = ?1 and u.id = ?2")
    Optional<Game> validateGameIdWithUsername(long gameId, long userId);

}
