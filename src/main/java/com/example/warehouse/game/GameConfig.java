package com.example.warehouse.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class GameConfig {
    URL url = new URL("http://baeldung.com");

    public GameConfig() throws MalformedURLException {
    }

//    @Bean
//    CommandLineRunner commandLineRunner(GameRepository repository){
//        return args -> {
//            Game cs = new Game("Counter Strike", "publisher 1", "genre1; genre2", 0,
//                    LocalDate.of(2001, 10, 20), "about game", "min req",
//                    url, url);
//            Game dota = new Game( "Dota 2", "publisher 1", "genre1; genre2", 0,
//                    LocalDate.of(2001, 10, 20), "about game", "min req",
//                    url, url);
//
//            repository.saveAll(List.of(cs, dota));
//        };
//    }
}
