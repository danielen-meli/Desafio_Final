package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.enums.OrderBy;
import com.meli.desafio_final.dto.BatchStockByDueDateResponseDto;
import com.meli.desafio_final.exception.BadRequestException;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.repository.IInboundOrderRepository;
import com.meli.desafio_final.repository.ISectionRepository;
import com.meli.desafio_final.util.TestUtilsGen_BatchStock;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BatchStockServiceTest {

    @InjectMocks
    BatchStockService batchStockService;

    @Mock
    IBatchStockRepository batchStockRepository;

    @Mock
    ISectionRepository sectionRepository;

    @Mock
    IInboundOrderRepository inboundOrderRepository;

    @BeforeEach
    public void setup() {

        BDDMockito.when(batchStockRepository.findAll())
                .thenReturn(TestUtilsGen_BatchStock.getNewListBatchStock());

        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(TestUtilsGen_BatchStock.getSectionMock());

        BDDMockito.when(sectionRepository.getSectionsIdsByCategory(ArgumentMatchers.any(Category.class)))
                .thenReturn(TestUtilsGen_BatchStock.getSectionsIdsByCategory());


        BDDMockito.when(inboundOrderRepository.findAllBySectionSectionId(ArgumentMatchers.any(Long.class)))
                .thenReturn(TestUtilsGen_BatchStock.getInboundOrderBySectionsMock());

        BDDMockito.when(inboundOrderRepository.getInboundOrder(ArgumentMatchers.<BigInteger>anyList()))
                .thenReturn(TestUtilsGen_BatchStock.getInboundOrderBySectionsMock());
    }

    @Test
    public void getBatchStocksByDueDate() {
        int numberOfDays = 200;
        long sectionId = 1;

        List<BatchStockByDueDateResponseDto> batchStocksDto = batchStockService.getBatchStocksByDueDate(numberOfDays, sectionId);

        assertThat(batchStocksDto).isNotNull();
        assertThat(batchStocksDto.size()).isEqualTo(2);
    }

  /*  @Test
    public void getBatchStocksByDueDate_WhenSectionWrong() {
        int numberOfDays = 200;
        long sectionId = 2;

        List<BatchStockByDueDateResponseDto> batchStocksDto = batchStockService.getBatchStocksByDueDate(numberOfDays, sectionId);
        assertThat(batchStocksDto).isNotNull();
        assertThat(batchStocksDto.size()).isEqualTo(2);

        assertThrows(BadRequestException.class, () -> {
            batchStockService.getBatchStocksByDueDate(numberOfDays, sectionId);
        });
    }*/


    @Test
    public void getBatchStocksFilteredBy() {
        int numberOfDays = 200;
        Category category = Category.REFRIGERATED;
        String orderTypeAsc = "asc";
        String orderTypeDesc = "desc";

        List<BatchStockByDueDateResponseDto> batchStocksDto = batchStockService.getBatchStocksFilteredBy(numberOfDays, category, orderTypeAsc);

        assertThat(batchStocksDto).isNotNull();
        assertThat(batchStocksDto.size()).isEqualTo(2);

        List<BatchStockByDueDateResponseDto> batchStocksDtoDesc = batchStockService.getBatchStocksFilteredBy(numberOfDays, category, orderTypeDesc);

        assertThat(batchStocksDtoDesc).isNotNull();
        assertThat(batchStocksDtoDesc.size()).isEqualTo(2);
    }

    @Test
    void getAllStockWhenPresent() {
        List<BatchStockDto> newListDto = TestUtilsGen_BatchStock.getNewListBStockDto();

        assertThat(batchStockService.getProductsInStock(1)).isEqualTo(newListDto);
    }

    @Test
    void getAllStockWhenNotPresent() {
        BDDMockito.when(batchStockRepository.findAll())
                .thenReturn(new ArrayList<>());

        NotFoundException message = assertThrows(NotFoundException.class,
                () -> {
                    batchStockService.getProductsInStock(2);
                });
        //nao existe produto cadastrado com id 2 em test util gen

        assertEquals("Não existem produtos em estoque.", message.getMessage());
    }

    @Test
    void getAllStockOrderedWhenPresent() {
        List<BatchStockDto> newListOrderedById = TestUtilsGen_BatchStock.getNewListOrderedByStockId();
        List<BatchStockDto> newListOrderedByQuantity = TestUtilsGen_BatchStock.getNewListOrderedByQuantity();
        List<BatchStockDto> newListOrderedByDueDate = TestUtilsGen_BatchStock.getNewListOrderedByDueDate();

        assertThat(batchStockService.getProductsInStockOrdered(1, OrderBy.L)).isEqualTo(newListOrderedById);
        assertThat(batchStockService.getProductsInStockOrdered(1, OrderBy.Q)).isEqualTo(newListOrderedByQuantity);
        assertThat(batchStockService.getProductsInStockOrdered(1, OrderBy.V)).isEqualTo(newListOrderedByDueDate);
    }
}