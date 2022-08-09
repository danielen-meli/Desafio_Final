package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrderRequestDto {
    private LocalDate date;
    private long buyerId;
    private Status orderStatus;
    List<OrderAdRequestDto> products;

}
