package com.meli.desafio_final.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PromoCode {
    @Id
    private String promoCode;
    private double discount;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public PromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
