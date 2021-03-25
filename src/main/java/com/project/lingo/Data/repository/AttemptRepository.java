package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    @Query(value = "select count(a) from Attempt a where a.teRadenWoord = ?1 and a.id = ?2")
    int getTotalTurns(String word, long id);

}
