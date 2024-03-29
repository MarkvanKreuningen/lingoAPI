package com.project.lingo.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/game2players").setViewName("game2players");
        registry.addViewController("/game1player").setViewName("game1player");
        registry.addViewController("/emailError").setViewName("emailError");
        registry.addViewController("/successRegister").setViewName("hello");
    }

}
