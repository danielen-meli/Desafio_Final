package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;
import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.model.enums.OrderBy;
import com.meli.desafio_final.service.IBatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/fresh-products")
public class BatchStockController {

    @Autowired
    private IBatchStockService batchStockService;

    /** Show product in stock if available when search by id.
     * @param productId type number long
     * @return the product asked for.
     */
    @GetMapping("/stock")
    public ResponseEntity<List<BatchStockDto>> getProductsInStock(@RequestParam("productId") long productId){
        return ResponseEntity.ok(batchStockService.getProductsInStock(productId));
    }

    /**
     * Show product in stock if available
     * @param orderBy the enum - L, Q, V;
     * @return a ordered list of products
     */
    @GetMapping("/stock/orderBy")
    public ResponseEntity<List<BatchStockDto>> getProductsInStockOrdered(@RequestParam("productId") long productId,
                                                                         @RequestParam("orderBy") OrderBy orderBy){
        return ResponseEntity.ok(batchStockService.getProductsInStockOrdered(productId, orderBy));
    }

    /**
     * Show product in stock available when the due date is between the interval asked for.
     * @param number_days - days to the due date.
     * @param section in the warehouse.
     * @return a list of products who are near to the due date.
     */
    @GetMapping("/due-date")
    public ResponseEntity<List<BatchStockByDueDateResponseDto>>
    getBatchStocksByDueDate(@RequestParam int number_days, @RequestParam long section) {
        return ResponseEntity.ok(batchStockService.getBatchStocksByDueDate(number_days, section));
    }

    /**
     * Show products in stock that are is between the interval, in the category and orderly.
     * @param number_days - days to the due date.
     * @param category is a enum, can be: FROZEN,REFRIGERATED,FRESH;
     * @param orderType - can be ascending (asc) ou descendant (desc)
     * @return a filtered list of products who are near to the due date, and belongs to the same category, and are orderly.
     */
    @GetMapping("/due-date/list")
    public ResponseEntity<List<BatchStockByDueDateResponseDto>> getBachStocksFilteredBy(
            @RequestParam int number_days,
            @RequestParam Category category,
            @RequestParam String orderType) {
        return ResponseEntity.ok(batchStockService.getBatchStocksFilteredBy(number_days, category, orderType));
    }
}
