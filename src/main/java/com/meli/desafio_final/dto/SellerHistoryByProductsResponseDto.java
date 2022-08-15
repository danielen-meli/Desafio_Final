package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.ISellerHistoryByProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SellerHistoryByProductsResponseDto {
    private long id;
    private double totalAlreadySold;
    private long sellerId;
    private String sellerName;

    public SellerHistoryByProductsResponseDto(ISellerHistoryByProduct sellerHistoryByProduct) {
        this.id = sellerHistoryByProduct.getId();
        this.totalAlreadySold = sellerHistoryByProduct.getTotal_quantity_sold();
        this.sellerId = sellerHistoryByProduct.getSeller_seller_id();
        this.sellerName = sellerHistoryByProduct.getSeller_name();
    }
}
