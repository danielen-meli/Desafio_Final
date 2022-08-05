package com.meli.desafio_final.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
public class BatchStockResponseDto {
    private double currentTemperature;
    private double minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private double volume;
    private LocalDate dueDate;
}
