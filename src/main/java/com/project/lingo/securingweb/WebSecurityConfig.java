package com.project.lingo.securingweb;

import com.project.lingo.application.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    //Update 17-02
    //werkt door httpBasic().and() toe te voegen. Wel nakijken hoe dit zit en wat dit nog meer toelaat
    //vanaf 52 tm 58 commenten als je wilt inloggen via postman en httpbasic aanzetten.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/", "/home", "/registration", "/login").permitAll()
                //.antMatchers("/game2players", "/game1player").permitAll()
                .antMatchers("/api/**", "/api/**/**").permitAll()
                .antMatchers("/resources/**","/static/**", "/css/**", "/js/**", "/img/**", "/icon/**").permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable();


        http./*httpBasic().and()*/authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/", "/home", "/register", "/login").permitAll()
                //.antMatchers("/registration", "/game2players", "/game1player").permitAll()
                .antMatchers("/api/**", "/api/**/**").permitAll()
                .antMatchers("/resources/**","/static/**", "/css/**", "/js/**", "/img/**", "/icon/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    
}
