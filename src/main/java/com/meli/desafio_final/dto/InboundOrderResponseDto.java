package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrderResponseDto {
    List<BatchStockResponseDto> batchStockList;
}
