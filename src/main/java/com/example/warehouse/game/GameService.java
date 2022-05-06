package com.example.warehouse.game;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Service
public class GameService {

    public List<Game> getGames() throws MalformedURLException {
        URL url = new URL("http://baeldung.com");

        return List.of(new Game(1L, "Counter Strike", "publisher 1", "genre1; genre2", 0,
                LocalDate.of(2001, 10, 20), "about game", "min req",
                url, url));
    }
}
