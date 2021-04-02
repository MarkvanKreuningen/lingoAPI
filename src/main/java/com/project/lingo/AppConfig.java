package com.project.lingo;

import com.project.lingo.application.*;
import com.project.lingo.data.repository.AttemptRepository;
import com.project.lingo.data.repository.GameRepository;
import com.project.lingo.data.repository.UserRepository;
import com.project.wordGenerator.application.IFilterFileService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

//https://docs.spring.io/spring-javaconfig/docs/1.0.0.m3/reference/html/creating-bean-definitions.html
//https://www.baeldung.com/spring-component-scanning
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
public class AppConfig {
    @Bean
    public ILingoService lingoService(GameRepository gameRepository){
        return new LingoService(gameRepository);
    }

    @Bean
    public IUserService userService(UserRepository repository, GameRepository gameRepository){
        return new UserService(repository, gameRepository);
    }
    @Bean
    public GameService gameService(GameRepository gameRepository, IFilterFileService filterFileService, IAttemptService attemptService){
        return new GameService(gameRepository, filterFileService, attemptService);
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public IAttemptService attemptService(AttemptRepository attemptRepository){
        return new AttemptService(attemptRepository);
    }
}
