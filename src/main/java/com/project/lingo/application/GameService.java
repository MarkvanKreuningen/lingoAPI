package com.project.lingo.application;

import com.project.lingo.data.repository.GameRepository;
import com.project.lingo.domain.Attempt;
import com.project.lingo.domain.Game;
import com.project.lingo.domain.User;
import com.project.lingo.presentation.dto.WordDto;
import com.project.lingo.presentation.error.*;
import com.project.wordGenerator.application.IFilterFileService;
import com.project.wordGenerator.application.IWordService;

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
    public Game findById(long id) throws GameNotFoundException {
        Optional<Game> object = repository.findById(id);
        if (!object.isPresent())
            throw new GameNotFoundException("Game not found");
        return object.get();
    }
    @Override
    public Game post(Game game){
        return repository.save(game);
    }

    @Override
    public WordDto start(long id) throws StartedException, GameOverException {
        Optional<Game> object = repository.findById(id);
        if (object.isPresent())
            return object.get().start(filterFileService, attemptService, this);
        else return null;
    }

    @Override
    public Game newGame(User user) {
        Game game = new Game(user);
        return post(game);
    }

    @Override
    public List<Game> findByUsername(String username) throws UserHasNoGamesException {
        List<Game> games = repository.findGamesForPlayerByUsername(username);
        if (games == null)
            throw new UserHasNoGamesException("User has no games");
        return games;
    }

    @Override
    public Object attemptWord(Game game, String word) throws TooLateException, GameOverException, WordNotValidException, NoAttemptsFoundException {
        Attempt lastAttempt = attemptService.getLastAttemptByGame(game);
        game.legalAttempt(lastAttempt, this);
        return game.userPlays(lastAttempt, this, word, attemptService, filterFileService);
    }

    @Override
    public Game validateGameUser(User user, long gameId) throws GameNotFoundException {
        if (findById(gameId) != null){
            Optional<Game> game = repository.validateGameIdWithUsername(gameId, user.getId());
            if (game.isPresent()){
                return game.get();
            } else throw new UserNotFoundException("User not found with this game");
        } else throw new GameNotFoundException("Game not found");
    }
}
