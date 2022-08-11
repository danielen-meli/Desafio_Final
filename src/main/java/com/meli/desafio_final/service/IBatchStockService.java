package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.model.BatchStock;

import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;
import com.meli.desafio_final.model.enums.Category;


import java.util.List;

public interface IBatchStockService {
    List<BatchStockByDueDateResponseDto> getBatchStocksFilteredBy(int numberOfDays, Category category, String asc);
    List<BatchStockDto> getProductsInStock(long productId);
    List<BatchStockDto> getProductsStockOrdered();
    List<BatchStockByDueDateResponseDto> getBatchStocksByDueDate(int numberOfDays, long sectionId);
}
