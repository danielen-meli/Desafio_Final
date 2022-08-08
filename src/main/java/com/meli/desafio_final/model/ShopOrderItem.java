package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
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
//    private ShopOrder shopOrder;
}
