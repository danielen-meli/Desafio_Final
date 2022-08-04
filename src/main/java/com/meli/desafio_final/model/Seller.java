package com.meli.desafio_final.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sellerId;

    @Column(nullable = false)
    private String sellerName;

    @OneToMany(mappedBy = "seller")
    private List<SellerAd> sellerAdList;
}
