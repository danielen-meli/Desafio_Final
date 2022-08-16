package com.meli.desafio_final.util;

import com.meli.desafio_final.model.PromoCode;

import java.time.LocalDate;

public class TestUtilsGen_PromoCode {

    public static PromoCode newPromoCode(){
        return PromoCode.builder().
                promoCode("SETEMBRO20").
                discount(0.80).
                dateStart(LocalDate.of(2022, 9, 1)).
                dateEnd(LocalDate.of(2022, 9, 30)).build();
    }
}
