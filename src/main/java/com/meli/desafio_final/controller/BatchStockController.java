package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.service.IBatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fresh-products/due-date/")
public class BatchStockController {

    @Autowired
    private IBatchStockService batchStockService;

    @GetMapping()
    public ResponseEntity<List<BatchStockByDueDateResponseDto>>
    getBatchStocksByDueDate(@RequestParam int number_days, @RequestParam long section ) {
        return ResponseEntity.ok(batchStockService.getBatchStocksByDueDate(number_days, section));
    }

/*    @GetMapping()
    public ResponseEntity<List<BatchStockByDueDateResponseDto>> getBatchStocksByDueDate(
            @RequestParam int number_days,
            @RequestParam Category category,
            @RequestParam String asc) {
        return null;
    }*/


}
