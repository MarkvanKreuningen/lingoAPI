package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "select s from Game s, User r where r.username = ?1 and s.user.id = r.id")
    List<Game> findGamesForPlayerByUsername(String gebruikersnaam);
}
