package com.meli.desafio_final.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ShopOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private double price;
    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sellerAd_id")
    private SellerAd sellerAd;

    public ShopOrderItem(int i, Object o, double v, int i1, Object o1) {
    }
//    private ShopOrder shopOrder;
}
