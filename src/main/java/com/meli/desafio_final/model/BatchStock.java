package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meli.desafio_final.dto.BatchStockRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchStockId;

    @ManyToOne
    @JsonIgnoreProperties("batchStockId")
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
    @JsonIgnoreProperties("batchStockList")
    @JoinColumn(name = "inbound_order_id")
    private InboundOrder inboundOrder;

    public BatchStock(BatchStockRequestDto batchStockRequestDto, SellerAd sellerAd, InboundOrder inboundOrder) {
        this.batchStockId = batchStockRequestDto.getBatchStockId();
        this.sellerAd = sellerAd;
        this.currentTemperature = batchStockRequestDto.getCurrentTemperature();
        this.minimumTemperature = batchStockRequestDto.getMinimumTemperature();
        this.initialQuantity = batchStockRequestDto.getInitialQuantity();
        this.currentQuantity = batchStockRequestDto.getCurrentQuantity();
        this.manufacturingDate = batchStockRequestDto.getManufacturingDate();
        this.manufacturingTime = batchStockRequestDto.getManufacturingTime();
        this.volume = batchStockRequestDto.getVolume();
        this.dueDate = batchStockRequestDto.getDueDate();
        this.inboundOrder = inboundOrder;
    }
}
