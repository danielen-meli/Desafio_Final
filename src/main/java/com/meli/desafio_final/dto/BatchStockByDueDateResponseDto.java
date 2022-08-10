package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockByDueDateResponseDto {
    private Long batchStockId;
    private LocalDate dueDate;
    private int currentQuantity;
    private Category category;
    private long sellerAdId;

    public BatchStockByDueDateResponseDto(BatchStock batchStock, Category category) {
        this.batchStockId = batchStock.getBatchStockId();
        this.dueDate = batchStock.getDueDate();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.category = category;
        this.sellerAdId = batchStock.getSellerAd().getSellerAdId();
    }

}
