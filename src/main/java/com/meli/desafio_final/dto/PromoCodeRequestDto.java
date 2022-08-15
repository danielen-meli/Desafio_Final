package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.PromoCode;
import lombok.Data;

@Data
public class PromoCodeRequestDto {
    private String promoCode;

    public PromoCodeRequestDto(PromoCode promoCode) {
        this.promoCode = promoCode.getPromoCode();
    }
}
