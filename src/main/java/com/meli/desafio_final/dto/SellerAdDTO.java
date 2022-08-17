package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SellerAdDTO {
    @NotNull
    private Product product;
    @NotNull
    private Double price;

    public SellerAdDTO(SellerAd sellerAd){
        this.product = sellerAd.getProduct();
        this.price = sellerAd.getPrice();
    }
}
