package com.meli.desafio_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountResponseDto {
    private String type;
    private double total;
    private double currentTotal;
}
