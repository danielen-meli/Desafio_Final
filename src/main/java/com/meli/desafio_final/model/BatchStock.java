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
    private long batchStockId;

    @ManyToOne
    private SellerAd sellerAd;

    @Column(nullable = false)
    private double currentTemperature;

    @Column(nullable = false)
    private double minimumTemperature;

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

    @ManyToOne
    private InboundOrder inboundOrder;

}
