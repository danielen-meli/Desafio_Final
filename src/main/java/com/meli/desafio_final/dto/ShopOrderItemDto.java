package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.ShopOrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopOrderItemDto {
    private int quantity;
    private SellerAdDTO sellerAdDTO;

    public ShopOrderItemDto(ShopOrderItem shopOrderItem) {
        this.quantity = shopOrderItem.getQuantity();
        this.sellerAdDTO = new SellerAdDTO(shopOrderItem.getSellerAd());
    }

    public static List<ShopOrderItemDto> convert(List<ShopOrderItem> shopOrderItem) {
        return shopOrderItem.stream().map(ShopOrderItemDto::new).collect(Collectors.toList());
    }
}
