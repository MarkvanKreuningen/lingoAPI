package com.project.lingo.Application;

import com.project.lingo.Data.repository.AttemptRepository;
import com.project.lingo.Domain.Attempt;
import com.project.lingo.Domain.Game;
import com.project.lingo.Presentation.dto.AttemptDto;
import com.project.lingo.Presentation.error.WordNotValid;
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

    @Override
    public Attempt getLastAttemptByGame(Game game) {
        return repository.getLastAttemptByGame(game.getId()).get(0);
    }

    @Override
    public AttemptDto getFeedback(Attempt attempt, String word, Game game) throws WordNotValid {
        System.out.println(attempt.toString());
        Attempt returnAttempt = repository.save(attempt.rateAttempt(attempt, word, game));
        return returnAttempt.getAttemptDto();
    }

    @Override
    public Attempt newAttempt(Game game, int lengthWord, IFilterFileService filterFileService, IAttemptService attemptService) {
        Attempt attempt = new Attempt(game, lengthWord, filterFileService, attemptService);
        post(attempt);
        return attempt;
    }


}
