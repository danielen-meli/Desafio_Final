package com.meli.desafio_final.service;

import com.meli.desafio_final.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InboundServiceTest {

    @InjectMocks
    InboundService inboundService;

    @Mock
    IInboundOrderRepository inboundOrderRepo;

    @Mock
    private IWarehouseRepository warehouseRepo;

    @Mock
    private ISectionRepository sectionRepo;

    @Mock
    private ISellerAdRepository sellerAdRepo;

    @Mock
    private IBatchStockRepository batchStockRepo;

//    @BeforeEach
//    public void setup() {
//        BDDMockito.when()
//    }

    @Test
    void insertNewInboundOrder() {
    }

    @Test
    void updateNewInboundOrder() {
    }
}