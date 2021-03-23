package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.SpelerService;
import com.project.lingo.Domain.Speler;
import com.project.lingo.Presentation.dto.SpelerDto;
import com.project.lingo.Presentation.error.SpelerAlreadyExistException;
import com.project.lingo.Presentation.util.GenericResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SpelerRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SpelerService spelerService;

    @RequestMapping(value = "/speler/registreren", method = RequestMethod.POST)
    public GenericResponse registerUserAccount(@Valid final SpelerDto accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", accountDto);

        try {
            final Speler registered = spelerService.registreerNieuwAccount(accountDto);
        } catch (final SpelerAlreadyExistException spelerAlreadyExistException){
            return new GenericResponse("SpelerAlreadyExistException", "Speler bestaat al");
        }

        return new GenericResponse("success");
    }
}
