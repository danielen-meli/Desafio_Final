package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.InboundOrderResponseDto;
import com.meli.desafio_final.service.IInboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundController {

    @Autowired
    IInboundService inboundService;

    /**
     * A seller sends an order and a batch of products to be sold.
     * @param inboundOrderRequestDto
     * @return  an answer for the seller showing the products who are available for sale.
     */
    @PostMapping("/inboundorder")
    public ResponseEntity<InboundOrderResponseDto> insertNewInboundOrder(@RequestBody @Valid InboundOrderRequestDto inboundOrderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inboundService.insertNewInboundOrder(inboundOrderRequestDto));
    }

    /**
     * The seller can edit his order of products to be sold.
     * @param inboundOrderRequestDto
     * @return an answer for the seller showing the products who are available for sale.
     */
    @PutMapping("/inboundorder")
    public ResponseEntity<InboundOrderResponseDto> updateInboundOrder(@RequestBody @Valid InboundOrderRequestDto inboundOrderRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inboundService.updateNewInboundOrder(inboundOrderRequestDto));
    }
}
