package com.jfboily.springbootrestapi.services;

import com.jfboily.springbootrestapi.data.Game;
import com.jfboily.springbootrestapi.data.GameRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{


    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames(String title) {
        return StringUtils.isNotBlank(title) ? gameRepository.findAllByTitleLike(title) : gameRepository.findAll();
    }

    @Override
    public Game getGameById(Integer id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGameById(Integer id){
        gameRepository.deleteById(id);
    }

    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    // Filtering with custom repository query

    @Override
    public List<Game> getMultiplayerGames(){
        return gameRepository.findAllByMultiplayer(true);
    }

    @Override
    public List<Game> getSinglePlayerGames(){
        return gameRepository.findAllByMultiplayer(false);
    }

    // Filtering with Java stream
    @Override
    public List<Game> getUpcomingGames() {
        var games = getAllGames(null);

        return games.stream().filter(g ->
                        LocalDate.now().isBefore(LocalDate.ofInstant(g.getReleaseDate().toInstant(), ZoneId.systemDefault())))
                .collect(Collectors.toList());
    }

    private Specification<Game> titleLike(String title){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("title"), "%"+title+"%");
    }



}
