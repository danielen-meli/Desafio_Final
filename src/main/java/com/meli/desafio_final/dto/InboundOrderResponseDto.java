package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class InboundOrderResponseDto {
    List<BatchStock> batchStockList;
}
