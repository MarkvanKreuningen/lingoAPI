package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.ILingoService;
import com.project.lingo.Application.ISpelService;
import com.project.lingo.Application.ISpelerService;
import com.project.lingo.Data.repository.SpelRepository;
import com.project.lingo.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpelController {
    private ILingoService lingoService;
    private ISpelerService spelerService;
    private ISpelService spelService;

    public SpelController(ILingoService lingoService, ISpelerService spelerService, ISpelService spelService) {
        this.lingoService = lingoService;
        this.spelerService = spelerService;
        this.spelService = spelService;
    }

    //Spring security toepassen om de objecten uit de securityContextHolder mee te nemen.
    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> start(){
        System.out.println("hello");
        try {
            //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Speler speler = spelerService.vindSpelerBijEmail("markvankreuningen@gmail.com");
            System.out.println("hello2");
            Spel spel = spelService.nieuwSpel(speler);
            return ResponseEntity.ok(spel.getId());
        } catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/start2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> start2(@RequestParam(required = false) String emailVanGebruiker) {
        try {
            Speler speler = spelerService.vindSpelerBijEmail(emailVanGebruiker);
            if (speler != null){
                return ResponseEntity.ok().body(lingoService.start(5, speler).toString());
            }
            return ResponseEntity.ok().body(lingoService.start(5, null).toString());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public ResponseEntity<String> play(@RequestParam String woordVanSpeler) {
        return ResponseEntity.ok(lingoService.spelerSpeelt(woordVanSpeler));
    }

    @GetMapping("/spel")
    public ResponseEntity<List<Spel>> getAlleSpellen(@RequestParam(required = false) String gebruikersnaam) {
        try {
            List<Spel> spellen = new ArrayList<>();

            if (gebruikersnaam == null)
                spellen.addAll(spelService.vindAlle());
            else
                spellen.addAll(spelerService.vindSpellenBySpelerGebruikersnaam(gebruikersnaam));

            if (spellen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/spel/{id}")
    public Spel getTestById(@PathVariable("id") long id) {
        return spelService.vindById(id);
    }

    /*@GetMapping("/spel/{id}")
    public ResponseEntity<Spel> getTestById(@PathVariable("id") long id) {
        Optional<Spel> spelData = spelRepository.findById(id);
        return spelData.map(spel -> new ResponseEntity<>(spel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @RequestMapping(value = "/start2spelers", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody LingoMet2Spelers getStartSpel(@RequestParam String naamSpeler1,
                                                         @RequestParam String naamSpeler2,
                                                         @RequestParam int tijdPerBeurt){
        return lingoService.gameMet2Spelers(naamSpeler1, naamSpeler2, tijdPerBeurt);
    }
}
