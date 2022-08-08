package com.meli.desafio_final.model.enums;

import lombok.Getter;


// @Enumerated(EnumType.STRING) passar essa anotacao pro model onde esta sendo usado a categoria
public enum Category {
    FROZEN("FROZEN", -18, -12), // 0
    REFRIGERATED("REFRIGERATED", 3, 10), //1
    FRESH("FRESH", 10, 15); //2

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
