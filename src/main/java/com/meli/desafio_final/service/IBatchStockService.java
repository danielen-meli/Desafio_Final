package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;

import java.util.List;

public interface IBatchStockService {
    List<BatchStockByDueDateResponseDto> getBatchStocksByDueDate(int numberOfDays, long sectionId);
}
