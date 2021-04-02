package com.project.lingo.presentation.controller;

import com.project.lingo.application.IUserService;
import com.project.lingo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SecureController {
    private IUserService userService;

    @GetMapping("/speler")
    public List<User> getAlleSpelers() {
        return userService.findAll();
    }
}
