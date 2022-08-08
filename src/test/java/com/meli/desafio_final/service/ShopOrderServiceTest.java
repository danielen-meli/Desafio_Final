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

import static org.assertj.core.api.Assertions.assertThat;
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

        ShopOrder shopOrder = service.getById(1L);


    }

}