package com.project.lingo.Presentation.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.lingo.Application.ExcludeProxiedFields;
import com.project.lingo.Application.ILingoService;
import com.project.lingo.Application.IGameService;
import com.project.lingo.Application.IUserService;
import com.project.lingo.Domain.*;
import com.project.lingo.Presentation.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity start(){
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserOfPrincipal(principal);
            Game newGame = gameService.newGame(user);
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeProxiedFields()).create();
            String json = gson.toJson(gameService.start(newGame.getId()));
            return ResponseEntity.ok(json);
        } catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String play(@RequestParam("gameId") long gameId,
                       @RequestParam("attempt") String attemptWord) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserOfPrincipal(principal);
            Game game = gameService.validateGameUser(user, gameId);
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeProxiedFields()).create();
            return gson.toJson(gameService.attemptWord(game, attemptWord));
        } catch (UserNotFoundException | GameNotFoundException | NewGameException | StartedException | GameOverException | TooLateException | WordNotValid e){
            e.printStackTrace();
        }

        return attemptWord;
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
    public Game getSpelById(@PathVariable("id") long id) throws GameNotFoundException {
        return gameService.findById(id);
    }

    @RequestMapping(value = "/start2spelers", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody LingoMet2Spelers getStartSpel(@RequestParam String naamSpeler1,
                                                         @RequestParam String naamSpeler2,
                                                         @RequestParam int tijdPerBeurt){
        return lingoService.gameMet2Spelers(naamSpeler1, naamSpeler2, tijdPerBeurt);
    }
}
