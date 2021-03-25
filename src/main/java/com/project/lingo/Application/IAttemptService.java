package com.project.lingo.Application;

import com.project.lingo.Data.repository.AttemptRepository;
import com.project.lingo.Domain.Attempt;
import org.springframework.stereotype.Service;

@Service
public interface IAttemptService {
    int getTotalTurns(String word, long gameid);
    Attempt post(Attempt attempt);
}
