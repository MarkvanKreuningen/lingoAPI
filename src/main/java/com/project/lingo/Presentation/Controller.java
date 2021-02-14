package com.project.lingo.Presentation;

import com.project.lingo.Application.LingoService;
import com.project.lingo.Application.ServiceProvider;
import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    LingoService lingoService = ServiceProvider.getLingoService();

    @GetMapping(value = "/start")
    public ResponseEntity<String> start(){
        return ResponseEntity.ok().body(lingoService.start(5));
    }

    @PostMapping(value = "/play")
    public ResponseEntity<String> play(@RequestParam String geradenWoord){
        return ResponseEntity.ok(lingoService.spelerSpeelt(geradenWoord));
    }
}
