package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Speler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpelerRepository extends CrudRepository<Speler, Long> {
    Speler findSpelerByEmail(String email);
    @Query(value = "select s from Speler s where s.gebruikersnaam = ?1")
    Speler findByGebruikersnaam(String gebruikersnaam);
}
