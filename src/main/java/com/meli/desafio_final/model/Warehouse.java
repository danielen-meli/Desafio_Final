package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String localization;

}
