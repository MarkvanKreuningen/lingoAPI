package com.project.lingo.application;

import com.project.lingo.data.repository.AttemptRepository;
import com.project.lingo.domain.Attempt;
import com.project.lingo.domain.Game;
import com.project.lingo.presentation.dto.AttemptDto;
import com.project.lingo.presentation.error.NoAttemptsFoundException;
import com.project.lingo.presentation.error.WordNotValidException;
import com.project.wordGenerator.application.IFilterFileService;
import com.project.wordGenerator.application.IWordService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AttemptService implements IAttemptService {
    private AttemptRepository repository;
    private IWordService wordService;

    public AttemptService(AttemptRepository repository, IWordService wordService){
        this.repository = repository;
        this.wordService = wordService;
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
    public AttemptDto getFeedback(Attempt attempt, String word, Game game) throws WordNotValidException {
        attempt.checkLegalAttempt(attempt, word, game, this);
        Attempt returnAttempt = repository.save(attempt.buildAttempt(attempt, word, game));
        return returnAttempt.getAttemptDto();
    }
    @Override
    public boolean validateWord(String word) {
        return wordService.validate(word);
    }

    @Override
    public Attempt newRoundNewWord(Game game, int lengthWord, IFilterFileService filterFileService, int round) {
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
