package com.meli.desafio_final.service;

import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IBatchStockRepository;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.util.TestUtilGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
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

    private Object Long;

    @Test
    public void get_returnShopOrder_WhenShopOrderValid(){
        BDDMockito.when(shopOrderRepository.findById(anyLong()))
                .thenReturn(Optional.of(TestUtilGenerator.newShopOrderToSave()));

        ShopOrder shopOrder = service.getById(1L);

        assertThat(shopOrder).isNotNull();
        assertThat(shopOrder.getOrderId()).isEqualTo(TestUtilGenerator.newShopOrderToSave().getOrderId());
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
        ShopOrder shopOrder = TestUtilGenerator.newShopOrderToSave();
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
        ShopOrder shopOrder = TestUtilGenerator.newShopOrderToSave();

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
        ShopOrder shopOrder = TestUtilGenerator.newShopOrderToSave();

        BDDMockito.when(shopOrderRepository.findById(anyLong())).thenReturn(Optional.of(shopOrder));
        BDDMockito.when(batchStockRepository.getQuantityProduct(anyLong())).thenReturn(10L);
        BDDMockito.when(shopOrderRepository.save(any())).thenReturn(shopOrder);

        ShopOrder shopOrderUpdated = service.closedShopOrder(shopOrder.getOrderId());

        assertThat(shopOrderUpdated.getStatus()).isEqualTo(Status.CLOSED);
    }

    @Test
    public void closedShopOrder_ShouldCloseShopOrderWhenHaveQuantitDiferentsBatch(){
        ShopOrder shopOrder = TestUtilGenerator.newShopOrderWithTwoBatch();

        BDDMockito.when(shopOrderRepository.findById(anyLong())).thenReturn(Optional.of(shopOrder));
        BDDMockito.when(batchStockRepository.getQuantityProduct(anyLong())).thenReturn(180L);
        BDDMockito.when(shopOrderRepository.save(any())).thenReturn(shopOrder);

        ShopOrder shopOrderUpdated = service.closedShopOrder(shopOrder.getOrderId());

        assertThat(shopOrderUpdated.getStatus()).isEqualTo(Status.CLOSED);
    }
}