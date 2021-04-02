package com.project.lingo.application;

import com.project.lingo.data.repository.AttemptRepository;
import com.project.lingo.domain.Attempt;
import com.project.lingo.domain.Game;
import com.project.lingo.presentation.dto.AttemptDto;
import com.project.lingo.presentation.error.NoAttemptsFoundException;
import com.project.lingo.presentation.error.WordNotValid;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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
    public Attempt getLastAttemptByGame(Game game) throws NoAttemptsFoundException {
        List<Attempt> attempt = repository.getLastAttemptByGame(game.getId());
        if (attempt.isEmpty()){
            throw new NoAttemptsFoundException("No attempt found for this game");
        }
        return attempt.get(0);
    }

    @Override
    public AttemptDto getFeedback(Attempt attempt, String word, Game game) throws WordNotValid {
        System.out.println(attempt.toString());
        Attempt returnAttempt = repository.save(attempt.rateAttempt(attempt, word, game));
        return returnAttempt.getAttemptDto();
    }

    @Override
    public Attempt newAttempt(Game game, int lengthWord, IFilterFileService filterFileService, int round) {
        Attempt attempt = new Attempt();
        attempt.setTeRadenWoord(lengthWord, filterFileService.getFilteredList());
        String wordToGuess = attempt.getTeRadenWoord();
        attempt.setTurn(getTotalTurns(wordToGuess, game.getId()));
        attempt.setGame(game);
        attempt.setRound(round);
        attempt.setCreated(new Timestamp(System.currentTimeMillis()));
        post(attempt);
        return attempt;
    }


}
