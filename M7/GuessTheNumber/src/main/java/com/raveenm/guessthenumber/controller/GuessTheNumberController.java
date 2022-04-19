/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.guessthenumber.controller;

import com.raveenm.guessthenumber.model.Game;
import com.raveenm.guessthenumber.model.Round;
import com.raveenm.guessthenumber.service.GuessTheNumberService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ravee
 */
@RestController
public class GuessTheNumberController {

    private final GuessTheNumberService service;

    public GuessTheNumberController(GuessTheNumberService service) {
        this.service = service;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        return service.addGame();
    }

    @PostMapping("/guess")
    public ResponseEntity<Round> create(@RequestBody Round round) {
        round.setTimeLog(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        round = service.addRound(round);
        if (round == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(round);
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/game/{game_id}")
    public ResponseEntity<Game> getGameById(@PathVariable int game_id){
        Game game = service.getGameById(game_id);
        if(game == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(game);
    }
    
    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundByGameId(@PathVariable int game_id){
        return service.getAllRoundsForGame(game_id);
        
    }

}
