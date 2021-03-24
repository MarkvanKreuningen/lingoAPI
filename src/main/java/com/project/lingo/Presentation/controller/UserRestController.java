package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.IUserService;
import com.project.lingo.Domain.User;
import com.project.lingo.Presentation.dto.SpelerDto;
import com.project.lingo.Presentation.error.SpelerAlreadyExistException;
import com.project.lingo.Presentation.util.GenericResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {
    private IUserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public UserRestController(IUserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public GenericResponse registerNewAccount(@Valid final SpelerDto accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", accountDto);

        try {
            final User registered = userService.createNewAccount(accountDto);
        } catch (final SpelerAlreadyExistException spelerAlreadyExistException){
            return new GenericResponse("SpelerAlreadyExistException", "Speler bestaat al");
        }

        return new GenericResponse("success");
    }
}
