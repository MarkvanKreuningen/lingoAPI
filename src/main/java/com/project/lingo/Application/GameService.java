package com.project.lingo.Application;

import com.project.lingo.Data.repository.GameRepository;
import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.AttemptDto;
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
    public Game findById(long id) throws GameNotFoundException {
        Optional<Game> object = repository.findById(id);
        System.out.println(object.toString());
        if (!object.isPresent())
            throw new GameNotFoundException("Game niet gevonden");
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
    public Game newGame(User user) {
        Game game = new Game(user);
        return post(game);
    }

    @Override
    public List<Game> findByUsername(String username) {
        List<Game> games = repository.findGamesForPlayerByUsername(username);
        if (games == null)
            throw new UserNotFoundException("Deze speler heeft geen games");
        return games;
    }

    @Override
    public Object attemptWord(Game game, String word) throws GameOverException, NewGameException, GameNotFoundException, TooLateException, WordNotValid, StartedException {
        return game.userPlays(attemptService.getLastAttemptByGame(game), this, word, attemptService, filterFileService);
    }

    @Override
    public Game validateGameUser(User user, long gameId) throws GameNotFoundException {
        if (findById(gameId) != null){
            Optional<Game> game = repository.validateGameIdWithUsername(gameId, user.getId());
            if (game.isPresent()){
                return game.get();
            } else throw new UserNotFoundException("User not found with this game");
        } else throw new GameNotFoundException("Game niet gevonden");
    }
}
