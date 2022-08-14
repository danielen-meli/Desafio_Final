package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.SellerHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SellerHistoryDto {
    private long id;
    private long sellerId;
    private String sellerName;
    private String sellerEmail;
    private long productId;
    private String productName;
    private double productPrice;
    private double quantityAlreadySold;

    public SellerHistoryDto(SellerHistory sellerHistory) {
        this.id = sellerHistory.getId();
        this.sellerId = sellerHistory.getSeller().getSellerId();
        this.sellerName = sellerHistory.getSeller().getSellerName();
        this.sellerEmail = sellerHistory.getSeller().getUser().getEmail();
        this.productId = sellerHistory.getSellerAd().getProduct().getProductId();
        this.productName = sellerHistory.getSellerAd().getProduct().getProductName();
        this.productPrice = sellerHistory.getSellerAd().getPrice();
        this.quantityAlreadySold = sellerHistory.getQuantity();
    }
}
