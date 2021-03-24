package com.project.lingo.Presentation.controller;

import com.project.lingo.Presentation.dto.SpelerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        SpelerDto spelerDto = new SpelerDto();
        model.addAttribute("speler", spelerDto);
        return "registration";
    }

}