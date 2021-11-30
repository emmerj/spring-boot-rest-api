package com.jfboily.springbootrestapi.controllers;

import com.jfboily.springbootrestapi.data.Game;
import com.jfboily.springbootrestapi.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<Game> getAll() {
        return gameService.getAllGames("");
    }

    @GetMapping(value = "/{id}")
    public Game getGameById(@PathVariable("id") Integer gameId){
        return gameService.getGameById(gameId);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGameById(@PathVariable("id") Integer gameId){
        gameService.deleteGameById(gameId);
    }

    @GetMapping(value = "/upcoming")
    public List<Game> getUpcomingGames(){
        return gameService.getUpcomingGames();
    }

    @GetMapping(value = "/singleplayer")
    public List<Game> getSinglePlayerGames(){
        return gameService.getSinglePlayerGames();
    }

    @GetMapping(value = "/multiplayer")
    public List<Game> getMultiplayerGames(){
        return gameService.getMultiplayerGames();
    }

    @PutMapping
    public Game addGame(@RequestBody Game game){
        return gameService.addGame(game);
    }

}
