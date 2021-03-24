package com.project.lingo.Application;

import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.SpelerDto;

import java.util.List;

public interface IUserService {
    String getUsernameOfPrincipal(Object principal);
    User createNewAccount(SpelerDto accountDto);
    User findByEmail(String email);
    List<Game> findGamesByUsername(String username);
    List<User> findAll();
}
