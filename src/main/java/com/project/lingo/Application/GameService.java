package com.project.lingo.Application;

import com.project.lingo.Data.repository.GameRepository;
import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.error.SpelNotFoundException;

import java.util.List;
import java.util.Optional;

public class GameService implements IGameService {
    private GameRepository repository;

    public GameService(GameRepository gameRepository){
        this.repository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        return (List<Game>) repository.findAll();
    }

    @Override
    public Game findById(long id) {
        Optional<Game> spel = repository.findById(id);
        if (!spel.isPresent())
            throw new SpelNotFoundException("Speler niet gevonden");
        return spel.get();
    }

    @Override
    public Game start() {
        return null;
    }

    @Override
    public Game nieuwSpel(User user) {
        Game game = new Game(user);
        return repository.save(game);
    }
}
