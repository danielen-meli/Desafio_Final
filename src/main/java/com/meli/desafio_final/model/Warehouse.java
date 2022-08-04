package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Warehouse {

    @Id
    private String name;
    private String localization;


}
