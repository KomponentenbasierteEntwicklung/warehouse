package com.example.warehouse.game;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

public class Game {
    private Long id;
    private String name;
    private String publisher;
    private String genres;
    private int requiredAge;
    private LocalDate releaseDate;
    private String aboutTheGame;
    private String minimumRequirement;
    private URL url;
    private URL backgroundImage;

    public Game() {
    }

    public Game(Long id, String name, String publisher, String genres, int requiredAge, LocalDate releaseDate, String aboutTheGame, String minimumRequirement, URL url, URL backgroundImage) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.genres = genres;
        this.requiredAge = requiredAge;
        this.releaseDate = releaseDate;
        this.aboutTheGame = aboutTheGame;
        this.minimumRequirement = minimumRequirement;
        this.url = url;
        this.backgroundImage = backgroundImage;
    }

    public Game(String name, String publisher, String genres, int requiredAge, LocalDate releaseDate, String aboutTheGame, String minimumRequirement, URL url, URL backgroundImage) {
        this.name = name;
        this.publisher = publisher;
        this.genres = genres;
        this.requiredAge = requiredAge;
        this.releaseDate = releaseDate;
        this.aboutTheGame = aboutTheGame;
        this.minimumRequirement = minimumRequirement;
        this.url = url;
        this.backgroundImage = backgroundImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(int requiredAge) {
        this.requiredAge = requiredAge;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAboutTheGame() {
        return aboutTheGame;
    }

    public void setAboutTheGame(String aboutTheGame) {
        this.aboutTheGame = aboutTheGame;
    }

    public String getMinimumRequirement() {
        return minimumRequirement;
    }

    public void setMinimumRequirement(String minimumRequirement) {
        this.minimumRequirement = minimumRequirement;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(URL backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genres='" + genres + '\'' +
                ", requiredAge=" + requiredAge +
                ", releaseDate=" + releaseDate +
                ", aboutTheGame='" + aboutTheGame + '\'' +
                ", minimumRequirement='" + minimumRequirement + '\'' +
                ", url=" + url +
                ", backgroundImage=" + backgroundImage +
                '}';
    }
}
