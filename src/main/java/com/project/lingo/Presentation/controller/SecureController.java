package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.IUserService;
import com.project.lingo.Domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class SecureController {
    private IUserService userService;

    @RolesAllowed("ADMIN")
    @GetMapping("/speler")
    public List<User> getAlleSpelers() {
        return userService.findAll();
    }
}
