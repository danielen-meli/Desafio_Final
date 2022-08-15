package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.PromoCode;
import com.meli.desafio_final.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrderRequestDto {
    @NotNull(message = "date is required")
    private LocalDate date;

    @NotNull(message = "buyerId is required")
    private Long buyerId;

    @NotNull(message = "orderStatus is required")
    private Status orderStatus;

    @Valid
    private List<OrderAdRequestDto> products;

    private PromoCodeRequestDto promoCode;
}
