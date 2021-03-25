package com.project.lingo.Application;

import com.project.lingo.Data.repository.AttemptRepository;
import com.project.lingo.Domain.Attempt;
import org.springframework.stereotype.Service;

@Service
public class AttemptService implements IAttemptService {
    private AttemptRepository repository;

    public AttemptService(AttemptRepository repository){
        this.repository = repository;
    }

    @Override
    public int getTotalTurns(String word, long gameid) {
        return repository.getTotalTurns(word, gameid);
    }

    @Override
    public Attempt post(Attempt attempt) {
        return repository.save(attempt);
    }
}
