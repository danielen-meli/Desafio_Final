package com.meli.desafio_final.repository;

import com.meli.desafio_final.dto.ShopOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShopOrderRepoTest {

    @Autowired
    private ShopOrderRepo shopOrderRepo;

    public void get_returnShopOrder_WhenShopOrderValid(){

    }

}