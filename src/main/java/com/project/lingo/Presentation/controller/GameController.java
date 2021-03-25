package com.project.lingo.Presentation.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.lingo.Application.ExcludeProxiedFields;
import com.project.lingo.Application.ILingoService;
import com.project.lingo.Application.IGameService;
import com.project.lingo.Application.IUserService;
import com.project.lingo.Domain.*;
import com.project.lingo.Presentation.dto.WordDto;
import com.project.lingo.Presentation.error.SpelerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity start(){
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserOfPrincipal(principal);
            Game newGame = gameService.nieuwSpel(user);
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeProxiedFields()).create();
            String json = gson.toJson(gameService.start(newGame.getId()));
            return ResponseEntity.ok(json);
        } catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> play(@RequestParam("gameId") long gameId,
                                       @RequestParam("attempt") String attemptWord) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserOfPrincipal(principal);
            if (user == null)
                throw new SpelerNotFoundException("player not found");

        } catch (SpelerNotFoundException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(lingoService.spelerSpeelt(attemptWord));
    }

    @GetMapping("/game")
    public ResponseEntity<List<Game>> getAlleSpellen(@RequestParam(required = false) String username) {
        try {
            List<Game> spellen = new ArrayList<>();

            if (username == null)
                spellen.addAll(gameService.findAll());
            else
                spellen.addAll(gameService.findByUsername(username));

            if (spellen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(spellen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<String> getSpelById(@PathVariable("id") long id) {
        Optional<String> spelData = Optional.ofNullable(gameService.findById(id).toString());
        return spelData.map(spel -> new ResponseEntity<>(spel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/start2spelers", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody LingoMet2Spelers getStartSpel(@RequestParam String naamSpeler1,
                                                         @RequestParam String naamSpeler2,
                                                         @RequestParam int tijdPerBeurt){
        return lingoService.gameMet2Spelers(naamSpeler1, naamSpeler2, tijdPerBeurt);
    }
}
