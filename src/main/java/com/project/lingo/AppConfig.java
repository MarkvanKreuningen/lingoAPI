package com.project.lingo;

import com.project.lingo.Application.*;
import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

//https://docs.spring.io/spring-javaconfig/docs/1.0.0.m3/reference/html/creating-bean-definitions.html
//https://www.baeldung.com/spring-component-scanning
//voor het schrijven van de testen
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
public class AppConfig {
    @Bean
    public ILingoService lingoService(SpelRepository spelRepository){
        return new LingoService(spelRepository);
    }

    @Bean
    public ISpelerService spelerService(SpelerRepository spelerRepository){
        return new SpelerService(spelerRepository);
    }
    @Bean
    public SpelService spelService(SpelRepository spelRepository){
        return new SpelService(spelRepository);
    }


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
