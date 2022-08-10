package com.meli.desafio_final.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockRequestDto {

    private long batchStockId;

    @NotNull(message = "sellerAdId is required")
    private Long sellerAdId;

    @NotNull(message = "currentTemperature is required")
    private Double currentTemperature;

    @NotNull(message = "minimumTemperature is required")
    private Double minimumTemperature;

    @NotNull(message = "initialQuantity is required")
    private Integer initialQuantity;

    @NotNull(message = "currentQuantity is required")
    private Integer currentQuantity;

    @NotNull(message = "manufacturingDate is required")
    private LocalDate manufacturingDate;

    @NotNull(message = "manufacturingTime is required")
    private LocalDateTime manufacturingTime;

    @NotNull(message = "volume is required")
    private Double volume;

    @NotNull(message = "dueDate is required")
    private LocalDate dueDate;
}
