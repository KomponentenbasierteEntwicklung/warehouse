package com.example.warehouse.game;

import com.example.warehouse.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() throws MalformedURLException {

        return gameRepository.findAll();
    }

    public void addNewGame(Game game) {
        gameRepository.save(game);
    }

    public void importGamesFromCSV(MultipartFile file) {
        try {
            List<Game> tutorials = CSVHelper.csvToGames(file.getInputStream());
            gameRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void deleteGame(Long gameId) {
        boolean exists = gameRepository.existsById(gameId);
        if(!exists){
            throw new IllegalStateException("Game with Id " + gameId + " doesn't exist");
        }
        gameRepository.deleteById(gameId);
    }

    @Transactional
    public void updateGame(Long gameId, String name, String publisher, String genres, int requiredAge, BigDecimal price) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException("game with id " + gameId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(game.getName(), name)){
            Optional<Game> optionalGame = gameRepository.findGameByName(name);
            if(optionalGame.isPresent()){
                throw new IllegalStateException("data with name " + name + " already exists.");
            }
            game.setName(name);
        }

        if(publisher != null && publisher.length() > 0 && !Objects.equals(game.getPublisher(), publisher)){
            game.setPublisher(publisher);
        }

        if(genres != null && genres.length() > 0 && !Objects.equals(game.getGenres(), genres)){
            game.setGenres(genres);
        }

        if(requiredAge > -1 && game.getRequiredAge() != requiredAge){
            game.setRequiredAge(requiredAge);
        }

        if(price != null && price.compareTo(BigDecimal.ZERO) > 0){
            game.setPrice(price);
        }
    }

    public Game getGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalStateException("game with id " + gameId + " does not exist"));
        return game;
    }
}
