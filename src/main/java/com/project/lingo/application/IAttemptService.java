package com.project.lingo.application;

import com.project.lingo.domain.Attempt;
import com.project.lingo.domain.Game;
import com.project.lingo.presentation.dto.AttemptDto;
import com.project.lingo.presentation.error.NoAttemptsFoundException;
import com.project.lingo.presentation.error.WordNotValid;
import com.project.wordGenerator.application.IFilterFileService;
import org.springframework.stereotype.Service;

@Service
public interface IAttemptService {
    int getTotalTurns(String word, long gameid);
    Attempt post(Attempt attempt);
    Attempt getLastAttemptByGame(Game Game) throws NoAttemptsFoundException;
    AttemptDto getFeedback(Attempt attempt, String word, Game game) throws WordNotValid;
    Attempt newAttempt(Game game, int lengthWord, IFilterFileService filterFileService, int round);
}
