package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.SpelerService;
import com.project.lingo.Domain.Speler;
import com.project.lingo.Presentation.dto.SpelerDto;
import com.project.lingo.Presentation.util.GenericResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SpelerRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SpelerService spelerService;

    @PostMapping("/speler/registreren")
    public GenericResponse registerUserAccount(@Valid final SpelerDto accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", accountDto);

        final Speler registered = spelerService.registreerNieuwAccount(accountDto);
        return new GenericResponse("success");
    }
}
