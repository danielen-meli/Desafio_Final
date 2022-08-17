package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.ShopOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopOrderDto {
    public long orderId;
    public String status;
    public Double precoTotal = .0;
    public List<ShopOrderItemDto> shopOrderItem;


    public ShopOrderDto(ShopOrder shopOrder) {
        this.orderId = shopOrder.getOrderId();
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
