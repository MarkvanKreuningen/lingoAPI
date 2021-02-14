package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Spel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpelRepository extends JpaRepository<Spel, Long> {
    List<Spel> findSpelsBySpeler_Gebruikersnaam(String gebruikersnaam);
}
