package com.project.lingo.Application;

import com.project.lingo.Data.repository.AttemptRepository;
import com.project.lingo.Domain.Attempt;
import com.project.lingo.Domain.Game;
import com.project.lingo.Presentation.dto.AttemptDto;
import com.project.lingo.Presentation.error.WordNotValid;
import org.springframework.stereotype.Service;

@Service
public interface IAttemptService {
    int getTotalTurns(String word, long gameid);
    Attempt post(Attempt attempt);
    Attempt getLastAttemptByGame(Game Game);
    AttemptDto getFeedback(Attempt attempt, String word, Game game) throws WordNotValid;
    Attempt newAttempt(Game game, int lengthWord, IFilterFileService filterFileService, IAttemptService attemptService);
}
