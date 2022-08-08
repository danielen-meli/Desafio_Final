package com.meli.desafio_final.model.enums;

import lombok.Getter;

public enum Category {
    FROZEN("FROZEN", -18, -12),
    REFRIGERATED("REFRIGERATED", 3, 10),
    FRESH("FRESH", 10, 15);

    @Getter
    private String name;
    public double minimumTemperature;
    public double maximumTemperature;

    Category(String name, double minimumTemperature, double maximumTemperature) {
        this.name = name;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
    }
}
