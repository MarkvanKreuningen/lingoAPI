package com.project.lingo.Presentation;

import com.project.lingo.Application.LingoService;
import com.project.lingo.Application.ServiceProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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