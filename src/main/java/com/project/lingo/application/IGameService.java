package com.project.lingo.application;

import com.project.lingo.domain.Game;
import com.project.lingo.domain.User;
import com.project.lingo.presentation.dto.WordDto;
import com.project.lingo.presentation.error.*;

import java.util.List;

public interface IGameService {
    List<Game> findAll();
    Game findById(long id) throws GameNotFoundException;
    WordDto start(long id) throws StartedException, GameOverException;
    Game newGame(User user);
    List<Game> findByUsername(String username) throws UserHasNoGamesException;
    Game post(Game game);
    Object attemptWord(Game game, String word) throws TooLateException, GameOverException, WordNotValid, NoAttemptsFoundException;
    Game validateGameUser(User user, long gameId) throws GameNotFoundException;
}
