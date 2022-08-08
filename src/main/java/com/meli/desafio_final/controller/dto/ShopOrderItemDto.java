package com.meli.desafio_final.controller.dto;

import com.meli.desafio_final.model.SellerAd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopOrderItemDto {
    private int quantity;
    private SellerAdDto sellerAd;
}
