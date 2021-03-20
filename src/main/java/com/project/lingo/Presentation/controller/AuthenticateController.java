package com.project.lingo.Presentation.controller;

import com.project.lingo.Domain.Speler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
public class AuthenticateController {

    @GetMapping("/speler/registratie")
    public String showRegistrationForm(WebRequest request, Model model) {
        Speler speler = new Speler();
        model.addAttribute("speler", speler);
        return "register";
    }

}
