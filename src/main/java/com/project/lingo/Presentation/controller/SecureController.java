package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.ISpelerService;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class SecureController {
    private ISpelerService spelerService;

    @RolesAllowed("ADMIN")
    @GetMapping("/speler")
    public List<Speler> getAlleSpelers() {
        return spelerService.vindAlle();
    }
}
