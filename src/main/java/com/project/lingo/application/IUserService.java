package com.project.lingo.application;

import com.project.lingo.domain.User;

public interface IUserService {
    String getUsernameOfPrincipal(Object principal);
    User getUserOfPrincipal(Object principal);
    User findByUsername(String username);
}
