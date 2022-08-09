package com.meli.desafio_final.service;

import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.util.TestUtilGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.meli.desafio_final.model.enums.Status.OPEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ShopOrderServiceTest {

    @InjectMocks
    private ShopOrderService service;

    @Mock
    private IShopOrderRepository repo;
    private Object Long;

    @Test
    public void get_returnShopOrder_WhenShopOrderValid(){
        BDDMockito.when(repo.findById(anyLong()))
                .thenReturn(Optional.of(TestUtilGenerator.newShopOrderToSave()));

        ShopOrder shopOrder = service.getById(1L);

        assertThat(shopOrder).isNotNull();
        assertThat(shopOrder.getOrderId()).isEqualTo(TestUtilGenerator.newShopOrderToSave().getOrderId());
    }

    @Test
    public void update_returnShopOrderUpdated_WhenShopValid(){
        ShopOrder shopOrder = TestUtilGenerator.newShopOrderToSave();

        BDDMockito.when(repo.save(shopOrder))
                .thenReturn(shopOrder);
        BDDMockito.when(repo.findById(anyLong()))
                .thenReturn(Optional.of(shopOrder));

        shopOrder.setStatus(OPEN);
        shopOrder.setOrderId(3);

        Map<String, Status> map = new HashMap<>();
        map.put("status", Status.CLOSED);
        ShopOrder shopOrderUpdated = service.updatePartial(shopOrder.getOrderId(), map);

        assertThat(shopOrderUpdated.getStatus()).isEqualTo(Status.CLOSED);
        assertThat(shopOrderUpdated.getOrderId()).isEqualTo(3L);

    }



}