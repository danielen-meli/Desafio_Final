package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Section;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.List;

@Data
public class InboundOrderRequestDto {

    private LocalDate orderDate;

    private long section;

    private List<BatchStock> batchStockList;
}
