package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class InboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderNumber;

    @Column(nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    private Section section;

    @OneToMany
    private List<BatchStock> batchStockList;

}
