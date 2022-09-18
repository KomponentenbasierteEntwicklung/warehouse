package com.example.warehouse.dlc;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;


// @Entity used for hibernate
// @Table for table in database
@Entity
public class Dlc {

    // map Game Class to database table
    @Id
    @SequenceGenerator(name = "dlc_sequence", sequenceName = "dlc_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "dlc_sequence")
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private URL headerImage;
    private String originalGame;
    private String description;  // seperated by ; for multiple
    private BigDecimal price;
    private LocalDate releaseDate;

    public Dlc() {
    }

    public Dlc(Long id, String name, URL headerImage, String originalGame, String description, BigDecimal price, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.headerImage = headerImage;
        this.originalGame = originalGame;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Dlc(String name, URL headerImage, String originalGame, String description, BigDecimal price, LocalDate releaseDate) {
        this.name = name;
        this.headerImage = headerImage;
        this.originalGame = originalGame;
        this.description = description;
        this.releaseDate = releaseDate;
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

    public URL getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(URL headerImage) {
        this.headerImage = headerImage;
    }

    public String getOriginalGame() {
        return originalGame;
    }

    public void setOriginalGame(String originalGame) {
        this.originalGame = originalGame;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "DLC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headerImage=" + headerImage +
                ", originalGame='" + originalGame + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}