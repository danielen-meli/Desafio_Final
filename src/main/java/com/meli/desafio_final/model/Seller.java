package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("seller")
    private List<SellerAd> sellerAdList;

    @OneToOne
    private User user;
}
