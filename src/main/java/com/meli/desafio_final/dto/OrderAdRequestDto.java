package com.meli.desafio_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAdRequestDto {
    @NotNull(message = "sellerAdId is required")
    private Long sellerAdId;

    @NotNull(message = "quantity is required")
    private Integer quantity;

}
