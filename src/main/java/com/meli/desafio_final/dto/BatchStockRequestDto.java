package com.meli.desafio_final.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BatchStockRequestDto {
    private long batchStockId;
    private long sellerAdId;
    private double currentTemperature;
    private double minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private double volume;
    private LocalDate dueDate;
}
