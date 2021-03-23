package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Spel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpelRepository extends CrudRepository<Spel, Long> {
    @Query(value = "select s from Spel s, Speler r where r.gebruikersnaam = ?1")
    List<Spel> vindSpellenVoorSpelerByGebruikersnaam(String gebruikersnaam);
}
