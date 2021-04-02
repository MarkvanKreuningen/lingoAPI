package com.project.lingo.presentation.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.lingo.application.ExcludeProxiedFields;
import com.project.lingo.application.IGameService;
import com.project.lingo.application.IUserService;
import com.project.lingo.domain.*;
import com.project.lingo.presentation.error.*;
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
    private IUserService userService;
    private IGameService gameService;

    public GameController(IUserService userService, IGameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    //Spring security toepassen om de objecten uit de securityContextHolder mee te nemen.
    @RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity start() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserOfPrincipal(principal);
            Game newGame = gameService.newGame(user);
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeProxiedFields()).create();
            String json = gson.toJson(gameService.start(newGame.getId()));
            return ResponseEntity.ok(json);
        } catch (Exception e) {
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
        } catch (UserNotFoundException | GameNotFoundException | GameOverException | TooLateException | WordNotValidException | NoAttemptsFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/game")
    public String getAlleSpellen(@RequestParam(required = false) String username) {
        try {
            List<Game> spellen = new ArrayList<>();

            if (username == null)
                spellen.addAll(gameService.findAll());
            else
                spellen.addAll(gameService.findByUsername(username));
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeProxiedFields()).create();
            return gson.toJson(spellen);
        } catch (UserNotFoundException | UserHasNoGamesException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Object> getSpelById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(gameService.findById(id), HttpStatus.OK);
        } catch (GameNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
