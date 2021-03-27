package com.project.lingo.Application;

import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.UserDto;

import java.util.List;

public interface IUserService {
    String getUsernameOfPrincipal(Object principal);
    User getUserOfPrincipal(Object principal);
    User createNewAccount(UserDto accountDto);
    User findByEmail(String email);
    User findByUsername(String username);
    List<User> findAll();
}
