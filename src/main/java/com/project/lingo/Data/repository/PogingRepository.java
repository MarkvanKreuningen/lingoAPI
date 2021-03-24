package com.project.lingo.Data.repository;

import com.project.lingo.Domain.Poging;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PogingRepository extends JpaRepository<Poging, Long> {

}
