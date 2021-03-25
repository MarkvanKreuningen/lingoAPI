package com.project.lingo.Application;

import com.project.lingo.Data.repository.GameRepository;
import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.*;

import java.util.List;
import java.util.Optional;

public class GameService implements IGameService {
    private GameRepository repository;
    private IFilterFileService filterFileService;
    private IAttemptService attemptService;

    public GameService(GameRepository gameRepository, IFilterFileService filterFileService, IAttemptService attemptService){
        this.repository = gameRepository;
        this.filterFileService = filterFileService;
        this.attemptService = attemptService;
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public Game findById(long id) {
        Optional<Game> object = repository.findById(id);
        if (!object.isPresent())
            throw new SpelNotFoundException("Spel niet gevonden");
        return object.get();
    }
    @Override
    public Game post(Game game){
        return repository.save(game);
    }

    @Override
    public WordDto start(long id) throws StartedException, GameOverException {
        Optional<Game> object = repository.findById(id);
        return object.get().start(filterFileService, attemptService, this);
    }

    @Override
    public Game nieuwSpel(User user) {
        Game game = new Game(user);
        return post(game);
    }

    @Override
    public List<Game> findByUsername(String username) {
        List<Game> games = repository.findGamesForPlayerByUsername(username);
        if (games == null)
            throw new SpelerNotFoundException("Deze speler heeft geen games");
        return games;
    }

    @Override
    public boolean attemptWord(long gameId, String word) throws GameOverException, NewGameException {
        Optional<Game> game = repository.findById(gameId);
        if (game.isPresent())
            return game.get().attempt();
        throw new SpelNotFoundException("Deze speler heeft geen games");
    }
}
