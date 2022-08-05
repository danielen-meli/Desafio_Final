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
    private long inboundOrderId;

    @Column(nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToMany(mappedBy = "inboundOrder")
    private List<BatchStock> batchStockList;


}
