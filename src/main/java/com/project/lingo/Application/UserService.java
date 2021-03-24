package com.project.lingo.Application;

import com.project.lingo.Data.repository.GameRepository;
import com.project.lingo.Data.repository.UserRepository;
import com.project.lingo.Domain.Game;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.SpelerDto;
import com.project.lingo.Presentation.error.SpelerAlreadyExistException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    private GameRepository gameRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, GameRepository gameRepository){
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public User createNewAccount(SpelerDto accountDto){
        if (emailExists(accountDto.getEmail())) {
            throw new SpelerAlreadyExistException("Er is al een account met dit emailadress");
        }

        final User user = new User();

        user.setUsername(accountDto.getGebruikersnaam());
        user.setRol(accountDto.getRole());
        user.setPassword(passwordEncoder.encode(accountDto.getWachtwoord()));
        user.setEmail(accountDto.getEmail());
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Game> findGamesByUsername(String gebruikersnaam) {
        System.out.println("hello");
        return gameRepository.findGamesForPlayerByUsername(gebruikersnaam);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
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
}
