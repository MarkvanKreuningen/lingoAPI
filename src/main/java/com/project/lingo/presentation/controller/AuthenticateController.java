package com.project.lingo.presentation.controller;

import com.project.lingo.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
public class AuthenticateController {

    @GetMapping("/speler/registratie")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("speler", user);
        return "register";
    }

}
