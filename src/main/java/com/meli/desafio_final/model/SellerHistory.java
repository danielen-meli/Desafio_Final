package com.meli.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties("sellerHistory")
    @ManyToOne
    private Seller seller;

    @JsonIgnoreProperties("sellerAdList")
    @ManyToOne
    private SellerAd sellerAd;

    private double quantity;

    public SellerHistory(ShopOrderItem shopOrderItem) {
        this.seller = shopOrderItem.getSellerAd().getSeller();
        this.sellerAd = shopOrderItem.getSellerAd();
        this.quantity = shopOrderItem.getQuantity();
    }
}
