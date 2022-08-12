package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.InboundOrderResponseDto;
import com.meli.desafio_final.model.BatchStock;

import java.util.List;


public interface IInboundService {
    InboundOrderResponseDto insertNewInboundOrder(InboundOrderRequestDto newInboundOrder);
    InboundOrderResponseDto updateNewInboundOrder(InboundOrderRequestDto newInboundOrder);

}
