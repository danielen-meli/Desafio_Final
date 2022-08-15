package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.ISellerHistoryTotalSold;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SellerHistoryTotalSoldResponseDto {
    private double totalSold;
    private long sellerId;
    private String sellerName;
    private String sellerEmail;

    public SellerHistoryTotalSoldResponseDto(ISellerHistoryTotalSold sellerHistoryTotalSold) {
        this.totalSold = sellerHistoryTotalSold.getTotal_sold();
        this.sellerId = sellerHistoryTotalSold.getSeller_id();
        this.sellerName = sellerHistoryTotalSold.getSeller_name();
        this.sellerEmail = sellerHistoryTotalSold.getSeller_email();
    }
}
