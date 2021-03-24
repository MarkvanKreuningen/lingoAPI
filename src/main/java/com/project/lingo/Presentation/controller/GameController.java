package com.project.lingo.Presentation.controller;

import com.project.lingo.Application.ILingoService;
import com.project.lingo.Application.IGameService;
import com.project.lingo.Application.IUserService;
import com.project.lingo.Domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private ILingoService lingoService;
    private IUserService userService;
    private IGameService gameService;

    public GameController(ILingoService lingoService, IUserService userService, IGameService gameService) {
        this.lingoService = lingoService;
        this.userService = userService;
        this.gameService = gameService;
    }

    //Spring security toepassen om de objecten uit de securityContextHolder mee te nemen.
    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> start(){
        try {
            //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findByEmail("markvankreuningen@gmail.com");
            Game game = gameService.nieuwSpel(user);
            return ResponseEntity.ok(game.getId());
        } catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/start2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> start2(@RequestParam(required = false) String emailVanGebruiker) {
        try {
            User user = userService.findByEmail(emailVanGebruiker);
            if (user != null){
                return ResponseEntity.ok().body(lingoService.start(5, user).toString());
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
    public ResponseEntity<List<Game>> getAlleSpellen(@RequestParam(required = false) String gebruikersnaam) {
        try {
            List<Game> spellen = new ArrayList<>();

            if (gebruikersnaam == null)
                spellen.addAll(gameService.findAll());
            else
                spellen.addAll(userService.findGamesByUsername(gebruikersnaam));

            if (spellen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/spel/{id}")
    public Game getTestById(@PathVariable("id") long id) {
        return gameService.findById(id);
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
