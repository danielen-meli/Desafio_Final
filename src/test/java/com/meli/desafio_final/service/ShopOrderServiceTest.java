package com.meli.desafio_final.service;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.util.TestUtilGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    public void get_returnShopOrder_WhenShopOrderValid(){
        BDDMockito.when(repo.findById(anyLong()))
                .thenReturn(Optional.of(TestUtilGenerator.newShopOrderToSave()));

        Optional<ShopOrder> shopOrder = Optional.ofNullable(service.getById(1L));

        assertThat(shopOrder.isEmpty()).isFalse();
        assertThat(shopOrder.get()).isNotNull();
        assertThat(shopOrder.get().getOrderId()).isEqualTo(TestUtilGenerator.newShopOrderToSave().getOrderId());
    }

    @Test
    public void update_returnShopOrderUpdated_WhenShopValid(){
        ShopOrder shopOrder = TestUtilGenerator.newShopOrderToSave();

        BDDMockito.when(repo.save(shopOrder))
                .thenReturn(shopOrder);
        BDDMockito.when(repo.findById(anyLong()))
                .thenReturn(Optional.of(shopOrder));

        shopOrder.setStatus(OPEN);

//        ShopOrder shopOrderUpdated = service.updatePartial(shopOrder.getOrderId(), Map<String, Status>);


    }

}