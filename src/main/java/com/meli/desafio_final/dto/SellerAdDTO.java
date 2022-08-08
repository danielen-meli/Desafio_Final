package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SellerAdDTO {
    @NotBlank
    private Product product;
    @NotBlank
    private double price;

    public SellerAdDTO(SellerAd sellerAd){
        this.product = sellerAd.getProduct();
        this.price = sellerAd.getPrice();
    }
}
