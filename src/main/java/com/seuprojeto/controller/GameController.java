package com.seuprojeto.controller;

import com.seuprojeto.model.Game;
import com.seuprojeto.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Retorna todos os jogos salvos no banco de dados
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    // Retorna um jogo específico por ID
    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    // Endpoint para criar um novo jogo
    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    // Atualiza um jogo existente identificado pelo ID
    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        return gameService.updateGame(id, game);
    }

    // Deleta um jogo pelo ID
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }

    // Endpoint que simula a coleta de dados de jogos (dados dummy)
    @GetMapping("/latest")
    public List<Game> getLatestGames() {
        return gameService.fetchLatestGames();
    }
}