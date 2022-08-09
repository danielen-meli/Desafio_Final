package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderRequestDto {

    private long id;

    private LocalDate orderDate;

    private long section;

    private List<BatchStockRequestDto> batchStockList;
}
