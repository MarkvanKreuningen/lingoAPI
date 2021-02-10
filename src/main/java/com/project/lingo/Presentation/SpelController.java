package com.project.lingo.Presentation;

import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Domain.Spel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spel")
public class SpelController {
    @Autowired
    SpelRepository spelRepository;

    @GetMapping()
    public ResponseEntity<List<Spel>> getAlleSpellen(){
        try {
            List<Spel> spellen = new ArrayList<>(spelRepository.findAll());
            if (spellen.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spel> getTestById(@PathVariable("id") long id) {
        Optional<Spel> spelData = spelRepository.findById(id);
        return spelData.map(spel -> new ResponseEntity<>(spel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/speler/{id}")
    public ResponseEntity<List<Spel>> getSpellenBySpeler(@PathVariable("id") long id) {
        try {
            List<Spel> spellen = new ArrayList<>(spelRepository.findSpelsBySpeler_Id(id));
            if (spellen.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/speler/{gebruikersnaam}")
    public ResponseEntity<List<Spel>> getSpellenBySpeler(@PathVariable("gebruikersnaam") String gebruikersnaam) {
        try {
            List<Spel> spellen = new ArrayList<>(spelRepository.findSpelsBySpeler_Gebruikersnaam(gebruikersnaam));
            if (spellen.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
