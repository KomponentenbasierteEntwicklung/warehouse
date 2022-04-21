package com.example.Warehouse;

import java.math.BigDecimal;

public class HardwareComponent {
    private final String name;
    private final String description;
    private final BigDecimal price;


    public HardwareComponent(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
