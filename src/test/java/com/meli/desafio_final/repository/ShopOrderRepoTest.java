package com.meli.desafio_final.repository;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.util.TestUtilsGeneratorShopOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ShopOrderRepoTest {

    @Autowired
    private IShopOrderRepository repository;

    @Test
    public void getById_returnShopOrder_WhenShopOrderValid(){
        ShopOrder shopOrder = TestUtilsGeneratorShopOrder.newShopOrderToSave();
        repository.save(shopOrder);

        ShopOrder shopFound = repository.findById(shopOrder.getOrderId()).get();

        assertThat(shopOrder.getOrderId()).isEqualTo(shopFound.getOrderId());

    }

    @Test
    public void getById_returnEmptyShopOrder_WhenShopOrderInvalid(){
        ShopOrder shopOrder = TestUtilsGeneratorShopOrder.newShopOrderToSave();
        repository.save(shopOrder);

        Optional<ShopOrder> shopFound = repository.findById(shopOrder.getOrderId());

        assertThat(shopFound.isEmpty()).isTrue();
    }

}