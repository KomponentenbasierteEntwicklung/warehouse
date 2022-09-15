package com.example.warehouse.helper;

import com.example.warehouse.game.Game;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "appid", "name", "publisher", "genres", "required_age", "release_date",
            "about_the_game", "minimum", "header_image", "background" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Game> csvToGames(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withQuote(null).withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Game> games = new ArrayList<Game>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Game game = new Game(
                        csvRecord.get("name"),
                        csvRecord.get("publisher"),
                        csvRecord.get("genres"),
                        Integer.parseInt(csvRecord.get("required_age")),
                        LocalDate.parse(csvRecord.get("release_date")),
                        csvRecord.get("about_the_game"),
                        csvRecord.get("minimum"),
                        new URL(csvRecord.get("header_image")),
                        new URL(csvRecord.get("backgroundURL"))
                );
                games.add(game);
            }
            return games;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


}
