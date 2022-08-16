package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.Buyer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BuyerResponseDto {
    private long buyerId;
    private String UserName;
    private String userEmail;
    private double totalPurchased;

    public BuyerResponseDto(Buyer buyer) {
        this.buyerId = buyer.getBuyerId();
        this.UserName = buyer.getUser().getUserName();
        this.userEmail = buyer.getUser().getEmail();
        this.totalPurchased = buyer.getQuantityPurchased();
    }
}
