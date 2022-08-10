package com.meli.desafio_final.dto;

import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderRequestDto {

    private long id;
    @NotNull(message = "OrderDate is required")
    private LocalDate orderDate;

    @NotNull(message = "Section is required")
    private Long section;

    @Valid
    private List<BatchStockRequestDto> batchStockList;
}
