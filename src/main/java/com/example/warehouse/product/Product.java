package com.example.warehouse.product;

import com.example.warehouse.dlc.Dlc;
import com.example.warehouse.game.Game;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_sequence")
    private Long id;
    private String name;
    @OneToOne(targetEntity=Game.class)
    private Game game;
    @OrderColumn
    @OneToMany(targetEntity=Dlc.class)
    private Dlc[] dlcs;
    private BigDecimal totalPrice;

    public Product() {
    }

    public Product(Game game, Dlc[] dlcs) {
        this.game = game;
        this.dlcs = dlcs;
    }

    public Product(Long id, String name, Game game, Dlc[] dlcs) {
        this.id = id;
        this.game = game;
        this.dlcs = dlcs;
        this.name = name;
        this.totalPrice = getTotalPrice();
    }

    public Product(Long id, Game game, Dlc[] dlcs) {
        this.id = id;
        this.game = game;
        this.name = game.getName();
        this.dlcs = dlcs;
        this.totalPrice = getTotalPrice();
    }

    public Product(Game game, List<Dlc> dlcs) {
        this.game = game;
        this.dlcs = dlcs.toArray(new Dlc[0]);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Dlc[] getDlcs() {
        return dlcs;
    }

    public void setDlcs(Dlc[] dlcs) {
        this.dlcs = dlcs;
    }

    public BigDecimal getTotalPrice() {
        if(game.getPrice() == null){
            throw new Error("game price can't be null");
        }
        BigDecimal totalPrice = game.getPrice();
        for (Dlc dlc : dlcs) {
            totalPrice.add(dlc.getPrice());
        }
        return totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", game=" + game +
                ", dlcs=" + Arrays.toString(dlcs) +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
