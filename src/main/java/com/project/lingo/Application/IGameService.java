package com.project.lingo.Application;

import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;

import java.util.List;

public interface IGameService {
    List<Game> findAll();
    Game findById(long id);
    Game start();
    Game nieuwSpel(User user);
}
