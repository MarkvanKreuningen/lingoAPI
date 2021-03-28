package com.project.lingo.Application;

import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.AttemptDto;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.*;

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
