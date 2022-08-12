package com.meli.desafio_final.service;
import static org.assertj.core.api.Assertions.assertThat;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.InboundOrderResponseDto;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.*;
import com.meli.desafio_final.util.TestUtilsGeneratorInboundOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    // TODO: CASES TO TEST

    /*
    * 1- Warehouse doesn't exists
    * 2- Section doesn't exists
    * 3- Section capacity is lower than stock0
    * 4- Products type is different from section selected
    * */

    // TODO: Methods to mock

    /*
    * INBOUND ORDER REPO:
    * 1- inboundOrderRepo.save
    * 2- inboundOrderRepo.findById
    *
    *
    * WAREHOUSE REPO
    * 1- warehouseRepo.findById
    *
    * SECTION REPO
    * 1- sectionRepo.findById
    * 2- sectionRepo.save
    *
    * SELLER AD REPO
    * 1- sellerAdRepo.findById
    *
    * BATCH STOCK REPO
    * 1- bachStockRepo.save
    *
    * */

    @BeforeEach
    public void setup() {
        BDDMockito.when(inboundOrderRepo.save(ArgumentMatchers.any(InboundOrder.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getInboundOrderMock().get());

        BDDMockito.when(inboundOrderRepo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getInboundOrderMock());

        BDDMockito.when(warehouseRepo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getWharehouseMock());

        BDDMockito.when(sectionRepo.save(ArgumentMatchers.any(Section.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getSectionMock().get());

        BDDMockito.when(sectionRepo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getSectionMock());

        BDDMockito.when(sellerAdRepo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getSellerAdMock());

        BDDMockito.when(batchStockRepo.save(ArgumentMatchers.any(BatchStock.class)))
                .thenReturn(TestUtilsGeneratorInboundOrder.getBatchStockMock());
    }

    @Test
    @DisplayName("Inserting new InboundOrder")
    void insertNewInboundOrder() {
        InboundOrderRequestDto inboundOrderRequestDtoUtil = TestUtilsGeneratorInboundOrder.getInboundOrderRequestDtoMock();
        InboundOrderResponseDto inboundOrderResponseDto = inboundService.insertNewInboundOrder(inboundOrderRequestDtoUtil);

        assertThat(inboundOrderResponseDto).isNotNull();
        assertThat(inboundOrderResponseDto.getBatchStockList()).isNotEmpty();
        assertThat(inboundOrderResponseDto.getBatchStockList().size()).isEqualTo(inboundOrderRequestDtoUtil.getBatchStockList().size());
    }

    @Test
    void updateNewInboundOrder() {
        InboundOrderRequestDto inboundOrderRequestDtoUtil = TestUtilsGeneratorInboundOrder.getInboundOrderRequestDtoUpdateMock();
        InboundOrderResponseDto inboundOrderResponseDto = inboundService.updateNewInboundOrder(inboundOrderRequestDtoUtil);

        assertThat(inboundOrderResponseDto).isNotNull();
        assertThat(inboundOrderResponseDto.getBatchStockList()).isNotEmpty();
        assertThat(inboundOrderResponseDto.getBatchStockList().size()).isEqualTo(inboundOrderRequestDtoUtil.getBatchStockList().size());
    }
}