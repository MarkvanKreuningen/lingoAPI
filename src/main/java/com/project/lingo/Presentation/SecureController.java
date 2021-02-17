package com.project.lingo.Presentation;

import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SecureController {
    @Autowired
    SpelerRepository spelerRepository;


    @GetMapping("/speler")
    public List<Speler> getAlleSpelers() {
        return spelerRepository.findAll();
    }
}
