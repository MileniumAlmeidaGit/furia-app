package com.seuprojeto.service;

import com.seuprojeto.model.Game;
import com.seuprojeto.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;


    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }


    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }


    public Game createGame(Game game) {
        return gameRepository.save(game);
    }


    public Game updateGame(Long id, Game gameDetails) {
        return gameRepository.findById(id).map(game -> {
            game.setHomeTeam(gameDetails.getHomeTeam());
            game.setAwayTeam(gameDetails.getAwayTeam());
            game.setGameDate(gameDetails.getGameDate());
            game.setStatus(gameDetails.getStatus());
            game.setResult(gameDetails.getResult());
            return gameRepository.save(game);
        }).orElse(null);
    }


    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
    public List<Game> fetchLatestGames() {
        Game game1 = new Game();
        game1.setHomeTeam("FURIA");
        game1.setAwayTeam("Team XYZ");
        game1.setGameDate(LocalDateTime.now().plusDays(1));
        game1.setStatus("Scheduled");
        game1.setResult(null);

        Game game2 = new Game();
        game2.setHomeTeam("FURIA");
        game2.setAwayTeam("Team ABC");
        game2.setGameDate(LocalDateTime.now().plusDays(2));
        game2.setStatus("Scheduled");
        game2.setResult(null);

        return Arrays.asList(game1, game2);
    }
}


