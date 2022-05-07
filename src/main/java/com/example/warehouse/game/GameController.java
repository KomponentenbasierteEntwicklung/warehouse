package com.example.warehouse.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames() throws MalformedURLException {
        return gameService.getGames();
    }

    @PostMapping
    public void registerNewGame(@RequestBody Game game){
        gameService.addNewGame(game);
    }

    @DeleteMapping(path = "{gameId}")
    public void deleteGame(@PathVariable("gameId") Long gameId){
        gameService.deleteGame(gameId);
    }

    @PutMapping(path = "{gameId}")
    public void updateGame(@PathVariable("gameId") Long gameId, @RequestParam(required = false) String name,
                           @RequestParam(required = false) String publisher, @RequestParam(required = false) String genres,
                           @RequestParam(required = false) int requiredAge){
        gameService.updateGame(gameId, name, publisher, genres, requiredAge);
    }
}
