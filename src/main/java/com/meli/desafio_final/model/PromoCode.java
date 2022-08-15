package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PromoCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPromoCode;
    private String promoCode;
    private double discount;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    @OneToMany(mappedBy = "promoCode")
    @JsonIgnoreProperties("promoCode")
    private List<ShopOrder> shopOrder;
}
