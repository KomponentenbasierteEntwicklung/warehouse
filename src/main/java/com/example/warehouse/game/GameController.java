package com.example.warehouse.game;

import com.example.warehouse.helper.CSVHelper;
import com.example.warehouse.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(path = "{gameId}")
    public Game getGame(@PathVariable("gameId") Long gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping
    public void registerNewGame(@Valid @RequestBody Game game){
        gameService.addNewGame(game);
    }

    @PostMapping("/import")
    public ResponseEntity<ResponseMessage> importFromCSV(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                gameService.importGamesFromCSV(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
