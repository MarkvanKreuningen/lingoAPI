package com.project.lingo.application;

import com.project.lingo.data.repository.UserRepository;
import com.project.lingo.domain.User;
import com.project.lingo.presentation.error.SpelerAlreadyExistException;
import com.project.lingo.presentation.error.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByUsername(String username) {
        if (usernameExists(username))
            return userRepository.findByUsername(username);
        else throw new UserNotFoundException("Player not found with username: "+username);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public String getUsernameOfPrincipal(Object principal) {
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public User getUserOfPrincipal(Object principal) {
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (usernameExists(username))
            return userRepository.findByUsername(username);
        else throw new UserNotFoundException("Player not found");

    }

}
