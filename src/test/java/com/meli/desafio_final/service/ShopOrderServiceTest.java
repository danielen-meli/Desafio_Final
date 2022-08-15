package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.dto.ShopOrderResponseDto;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.repository.IBuyerRepository;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import com.meli.desafio_final.util.TestUtilsGeneratorInboundOrder;
import com.meli.desafio_final.util.TestUtilsGeneratorShopOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ShopOrderServiceTest {

    @InjectMocks
    private ShopOrderService service;

    @Mock
    private IShopOrderRepository shopOrderRepository;

    @Mock
    private IBatchStockRepository batchStockRepository;

    @Mock
    private ISellerAdRepository sellerAdRepository;

    @Mock
    private IBuyerRepository buyerRepository;

    private Object Long;


    @Test
    public void get_returnShopOrder_WhenShopOrderValid(){
        BDDMockito.when(shopOrderRepository.findById(anyLong()))
                .thenReturn(Optional.of(TestUtilsGeneratorShopOrder.newShopOrderToSave()));

        ShopOrder shopOrder = service.getById(1L);

        assertThat(shopOrder).isNotNull();
        assertThat(shopOrder.getOrderId()).isEqualTo(TestUtilsGeneratorShopOrder.newShopOrderToSave().getOrderId());
    }

    @Test
    public void getById_ShouldReturnException_WhenIdNotfound(){
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.getById(11L);
        });

        assertThat(exception.getMessage()).isEqualTo("Shop order not found");
    }

    @Test
    public void closedShopOrder_ShouldReturnException_WhenShopOrderAlreadyClosed(){
        ShopOrder shopOrder = TestUtilsGeneratorShopOrder.newShopOrderToSave();
        shopOrder.setStatus(Status.CLOSED);

        BDDMockito.when(shopOrderRepository.findById(anyLong()))
                .thenReturn(Optional.of(shopOrder));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.closedShopOrder(shopOrder.getOrderId());
        });

        assertThat(exception.getMessage().equals("carrinho ja esta fechado"));
        assertThat(exception.getClass()).isEqualTo(IllegalArgumentException.class);
    }

    @Test
    public void closedShopOrder_ShouldReturnException_WhenNotHaveQuantityInStorage(){
        ShopOrder shopOrder = TestUtilsGeneratorShopOrder.newShopOrderToSave();

        BDDMockito.when(shopOrderRepository.findById(anyLong())).thenReturn(Optional.of(shopOrder));
        BDDMockito.when(batchStockRepository.getQuantityProduct(anyLong())).thenReturn(0l);


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.closedShopOrder(shopOrder.getOrderId());
        });

        assertThat(exception.getMessage().contains("Estoque nao possui quantidade suficiente de "));
        assertThat(exception.getClass()).isEqualTo(IllegalArgumentException.class);
    }

    @Test
    public void closedShopOrder_ShouldCloseShopOrderWhenHaveQuantityInSameBatch(){
        ShopOrder shopOrder = TestUtilsGeneratorShopOrder.newShopOrderToSave();

        BDDMockito.when(shopOrderRepository.findById(anyLong())).thenReturn(Optional.of(shopOrder));
        BDDMockito.when(batchStockRepository.getQuantityProduct(anyLong())).thenReturn(10L);
        BDDMockito.when(shopOrderRepository.save(any())).thenReturn(shopOrder);

        ShopOrder shopOrderUpdated = service.closedShopOrder(shopOrder.getOrderId());

        assertThat(shopOrderUpdated.getStatus()).isEqualTo(Status.CLOSED);
    }

    @Test
    public void insertNewShopOrder () {
        ShopOrder shopOrderSaved = TestUtilsGeneratorShopOrder.getShopOrderMock();
        BDDMockito.when(shopOrderRepository.save(ArgumentMatchers.any(ShopOrder.class)))
                .thenReturn(shopOrderSaved);
        BDDMockito.when(sellerAdRepository.findById(anyLong()))
                .thenReturn(Optional.of(TestUtilsGen_SellerAd.getSellerAd()));
        BDDMockito.when(buyerRepository.findById(anyLong()))
                .thenReturn(Optional.of(TestUtilsGeneratorShopOrder.generatedBuyer()));
        BDDMockito.when(batchStockRepository.findAllBySellerAdSellerAdId(anyLong()))
                .thenReturn(TestUtilsGeneratorInboundOrder.getBatchStockListMock());

        ShopOrderRequestDto shopOrderRequestDtoUtil = TestUtilsGeneratorShopOrder.getShopOrderRequestDtoMock();
        ShopOrderResponseDto shopOrderResponseDto = service.insertNewShopOrder(shopOrderRequestDtoUtil);


        assertThat(shopOrderResponseDto).isNotNull();
        assertThat(shopOrderResponseDto.getTotalPrice()).isPositive();
        assertThat(shopOrderResponseDto.getTotalPrice()).isEqualTo(315.00);
    }


    public void discountsAvailable(){
        
    }
}