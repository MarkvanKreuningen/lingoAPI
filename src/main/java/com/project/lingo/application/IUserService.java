package com.project.lingo.application;

import com.project.lingo.domain.User;
import com.project.lingo.presentation.dto.UserDto;

import java.util.List;

public interface IUserService {
    String getUsernameOfPrincipal(Object principal);
    User getUserOfPrincipal(Object principal);
    User createNewAccount(UserDto accountDto);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();
}
