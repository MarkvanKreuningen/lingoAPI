package com.project.lingo;

import com.project.lingo.application.*;
import com.project.lingo.data.repository.AttemptRepository;
import com.project.lingo.data.repository.GameRepository;
import com.project.lingo.data.repository.UserRepository;
import com.project.wordGenerator.application.IFilterFileService;
import com.project.wordGenerator.application.IWordService;
import com.project.wordGenerator.application.WordService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;

//https://docs.spring.io/spring-javaconfig/docs/1.0.0.m3/reference/html/creating-bean-definitions.html
//https://www.baeldung.com/spring-component-scanning
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
public class AppConfig {
    @Bean
    public IUserService userService(UserRepository repository){
        return new UserService(repository);
    }
    @Bean
    public GameService gameService(GameRepository gameRepository, IFilterFileService filterFileService, IAttemptService attemptService){
        return new GameService(gameRepository, filterFileService, attemptService);
    }

    @Bean
    public IAttemptService attemptService(AttemptRepository attemptRepository, IWordService wordService){
        return new AttemptService(attemptRepository, wordService);
    }

    @Bean
    public IWordService wordService(IFilterFileService filterFileService) throws NoSuchAlgorithmException {
        return new WordService(filterFileService);
    }
}
