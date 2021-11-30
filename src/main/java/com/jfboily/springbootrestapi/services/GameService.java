package com.jfboily.springbootrestapi.services;

import com.jfboily.springbootrestapi.data.Game;

import java.util.List;

public interface GameService {

    List<Game> getAllGames(String title);

    List<Game> getUpcomingGames();

    List<Game> getMultiplayerGames();

    List<Game> getSinglePlayerGames();

    Game getGameById(Integer id);

    void deleteGameById(Integer id);

    Game addGame(Game game);
}
