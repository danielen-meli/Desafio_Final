package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class BatchStockDto {
    private long sectionId;
    private long warehouseId;
    private long productId;
    private long batchStockId;
    private int currentQuantity;
    private LocalDate dueDate;

    public BatchStockDto(BatchStock batchStock) {
        this.sectionId = batchStock.getInboundOrder().getSection().getSectionId();
        this.warehouseId = batchStock.getInboundOrder().getSection().getWarehouse().getWarehouseId();
        this.productId = batchStock.getSellerAd().getProduct().getProductId();
        this.batchStockId = batchStock.getBatchStockId();
        this.currentQuantity = batchStock.getCurrentQuantity();
        this.dueDate = batchStock.getDueDate();
    }
}
