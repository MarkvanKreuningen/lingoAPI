package com.project.lingo;

import com.project.lingo.Application.*;
import com.project.lingo.Data.repository.GameRepository;
import com.project.lingo.Data.repository.UserRepository;
import com.project.lingo.Domain.Game;
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
    public GameService gameService(GameRepository gameRepository){
        return new GameService(gameRepository);
    }

    /*@Bean
    public UserDetailsService userDetailsService(UserRepository repository){
        return new MyUserDetailsService(repository);
    }*/


    /*@Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }*/
}