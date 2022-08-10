package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BatchStockResponseDto {
    private double currentTemperature;
    private double minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private double volume;
    private LocalDate dueDate;

    public BatchStockResponseDto(BatchStock batchStock) {
        this.currentTemperature = batchStock.getCurrentTemperature();
        this.minimumTemperature = batchStock.getMinimumTemperature();
        this.initialQuantity = batchStock.getInitialQuantity();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.manufacturingDate = batchStock.getManufacturingDate();
        this.manufacturingTime = batchStock.getManufacturingTime();
        this.volume = batchStock.getVolume();
        this.dueDate = batchStock.getDueDate();
    }
}
