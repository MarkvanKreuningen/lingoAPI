package com.project.lingo.user;

import com.project.lingo.AppConfig;
import com.project.lingo.application.UserService;
import com.project.lingo.data.repository.UserRepository;
import com.project.lingo.domain.User;
import org.apache.catalina.realm.GenericPrincipal;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import java.security.Principal;
import java.util.Collections;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserServiceTest {
    @InjectMocks
    private static UserService userService;
    @Mock
    private static UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("return user from principal object")
    void checkIfUserExistsFromPrincipalObject(){

    }
}
