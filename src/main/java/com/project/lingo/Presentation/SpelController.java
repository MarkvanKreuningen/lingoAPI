package com.project.lingo.Presentation;

import com.project.lingo.Application.LingoService;
import com.project.lingo.Application.ServiceProvider;
import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Data.repository.SpelerRepository;
import com.project.lingo.Domain.Spel;
import com.project.lingo.Domain.Speler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpelController {
    @Autowired
    SpelRepository spelRepository;

    LingoService lingoService = ServiceProvider.getLingoService();

    @GetMapping(value = "/start")
    public ResponseEntity<String> start() {
        return ResponseEntity.ok().body(lingoService.start(5));
    }

    //! WERKT NOG NIET
    @PostMapping(value = "/play")
    public ResponseEntity<String> play(@RequestParam String geradenWoord) {
        return ResponseEntity.ok(lingoService.spelerSpeelt(geradenWoord));
    }

    @GetMapping("/spel")
    public ResponseEntity<List<Spel>> getAlleSpellen(@RequestParam(required = false) String gebruikersnaam) {
        try {
            List<Spel> spellen = new ArrayList<>();

            if (gebruikersnaam == null)
                spellen.addAll(spelRepository.findAll());
            else
                spellen.addAll(spelRepository.findSpelsBySpeler_Gebruikersnaam(gebruikersnaam));

            if (spellen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/spel/{id}")
    public ResponseEntity<Spel> getTestById(@PathVariable("id") long id) {
        Optional<Spel> spelData = spelRepository.findById(id);
        return spelData.map(spel -> new ResponseEntity<>(spel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
