package com.meli.desafio_final.controller;

import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.InboundOrderResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundController {

    @GetMapping("ping")
    public ResponseEntity<String> testeGet() {
        return ResponseEntity.ok("pong!");
    }

    @PostMapping("/inboundorder")
    public ResponseEntity<InboundOrderResponseDto> insertNewInboundOrder(@RequestBody InboundOrderRequestDto inboundOrderRequestDto) {
        return null;
    }

    @PutMapping("/inboundorder")
    public ResponseEntity<InboundOrderResponseDto> updateInboundOrder(@RequestBody InboundOrderRequestDto inboundOrderRequestDto) {
        return null;
    }
}
