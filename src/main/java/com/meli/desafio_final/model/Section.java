package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Section {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionId;
    private String wareHouseCode;
    private long sectionCode;
    private double sectionTemperature;
}
