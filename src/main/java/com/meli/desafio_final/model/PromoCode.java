package com.meli.desafio_final.model;

import com.meli.desafio_final.model.enums.DiscountType;
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
    private String promoCode;
    private double discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private double minimum;

//    @OneToMany(mappedBy = "promoCode", cascade = CascadeType.PERSIST)
//    private List<ShopOrder> shopOrders;

    public PromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
