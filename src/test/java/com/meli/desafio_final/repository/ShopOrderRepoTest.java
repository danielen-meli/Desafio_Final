package com.meli.desafio_final.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShopOrderRepoTest {

    @Autowired
    private IShopOrderRepository shopOrderRepo;

    public void get_returnShopOrder_WhenShopOrderValid(){

    }

}