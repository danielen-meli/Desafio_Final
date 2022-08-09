package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
