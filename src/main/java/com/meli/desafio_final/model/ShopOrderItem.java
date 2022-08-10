package com.meli.desafio_final.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sellerAd_id")
    private SellerAd sellerAd;

    //TODO ver se esse construtor está sendo usado
    public ShopOrderItem(int i, Object o, double v, int i1, Object o1) {
    }

    public ShopOrderItem(LocalDate date, int quantity, SellerAd sellerAd) {
        this.date = date;
        this.price = sellerAd.getPrice();
        this.quantity = quantity;
        this.sellerAd = sellerAd;
    }
}
