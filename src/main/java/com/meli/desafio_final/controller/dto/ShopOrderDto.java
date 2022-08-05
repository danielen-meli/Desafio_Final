package com.meli.desafio_final.controller.dto;

import com.meli.desafio_final.model.Buyer;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.ShopOrderItem;
import com.meli.desafio_final.model.enums.Status;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopOrderDto {
    public long orderId;
    public String status;
    public List<ShopOrderItem> shopOrderItem;
    public Buyer buyer;

    public ShopOrderDto(ShopOrder shopOrder) {
        this.orderId = shopOrder.getOrderId();
        this.status = shopOrder.getStatus().toString();
        this.shopOrderItem = shopOrder.getShopOrderItem();
        this.buyer = shopOrder.getBuyer();
    }

    public static ShopOrderDto converter(ShopOrder shopOrder){
        return new ShopOrderDto(shopOrder);
    }
}
