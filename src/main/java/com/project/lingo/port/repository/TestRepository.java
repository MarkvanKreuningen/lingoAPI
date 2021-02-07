package com.project.lingo.port.repository;

import java.util.List;

import com.project.lingo.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findById(long id);


}