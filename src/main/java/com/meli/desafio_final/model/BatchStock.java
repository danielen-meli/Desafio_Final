package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class BatchStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int batchNumber;

    @ManyToOne
    private SellerAd sellerAd;

    @Column(nullable = false)
    private double currentTemperature;

    @Column(nullable = false)
    private int initialQuantity;

    @Column(nullable = false)
    private int currentQuantity;

    @Column(nullable = false)
    private LocalDate manufacturingDate;

    @Column(nullable = false)
    private LocalDateTime manufacturingTime;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private LocalDate dueDate;

}
