package com.meli.desafio_final.controller.payment.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentItemDto {
    private String id;
    private String title;
    private String description;
    private String categoryId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
