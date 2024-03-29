package com.example.warehouse.game;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

// @Entity used for hibernate

@Entity
public class Game implements Serializable {
    // map Game Class to database table
    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "game_sequence")
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String publisher;
    private String genres;  // seperated by ; for multiple
    private int requiredAge;
    private LocalDate releaseDate;
    private String aboutTheGame;
    private String minimumRequirement;
    private URL imageUrl;
    private URL backgroundImage;
    private BigDecimal price;


    public Game() {
    }

    public Game(Long id, String name, String publisher, String genres, int requiredAge, LocalDate releaseDate, String aboutTheGame, String minimumRequirement, URL imageUrl, URL backgroundImage, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.genres = genres;
        this.requiredAge = requiredAge;
        this.releaseDate = releaseDate;
        this.aboutTheGame = aboutTheGame;
        this.minimumRequirement = minimumRequirement;
        this.imageUrl = imageUrl;
        this.backgroundImage = backgroundImage;
        this.price = price;
    }

    public Game(String name, String publisher, String genres, int requiredAge, LocalDate releaseDate, String aboutTheGame, String minimumRequirement, URL imageUrl, URL backgroundImage, BigDecimal price) {
        this.name = name;
        this.publisher = publisher;
        this.genres = genres;
        this.requiredAge = requiredAge;
        this.releaseDate = releaseDate;
        this.aboutTheGame = aboutTheGame;
        this.minimumRequirement = minimumRequirement;
        this.imageUrl = imageUrl;
        this.backgroundImage = backgroundImage;
        this.price = price;
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

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public URL getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(URL backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
                ", url=" + imageUrl +
                ", backgroundImage=" + backgroundImage +
                ", price=" + price +
                '}';
    }
}
