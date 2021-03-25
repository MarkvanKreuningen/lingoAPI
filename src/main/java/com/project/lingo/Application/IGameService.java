package com.project.lingo.Application;

import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.GameOverException;
import com.project.lingo.Presentation.error.NewGameException;
import com.project.lingo.Presentation.error.StartedException;

import java.util.List;

public interface IGameService {
    List<Game> findAll();
    Game findById(long id);
    WordDto start(long id) throws StartedException, GameOverException;
    Game nieuwSpel(User user);
    List<Game> findByUsername(String username);
    Game post(Game game);
    boolean attemptWord(long gameId, String word) throws GameOverException, StartedException, NewGameException;
}
