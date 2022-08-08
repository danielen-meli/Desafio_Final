package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.ShopOrderItem;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopOrderDto {
    public String status;
    public Double precoTotal = .0;
    public List<ShopOrderItemDto> shopOrderItem;

    public ShopOrderDto(ShopOrder shopOrder) {
        this.status = shopOrder.getStatus().toString();
        this.shopOrderItem = ShopOrderItemDto.convert(shopOrder.getShopOrderItem());
        calcularTotal();
    }

    public Double calcularTotal(){
        shopOrderItem.forEach(i -> {
            precoTotal += i.getSellerAdDTO().getPrice() * i.getQuantity();
        });

        return precoTotal;
    }
}
