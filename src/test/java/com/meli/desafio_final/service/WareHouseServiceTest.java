package com.meli.desafio_final.service;

import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.Warehouse;
import com.meli.desafio_final.repository.IWarehouseRepository;
import com.meli.desafio_final.util.TestUtilGeneratorWareHouse;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@DataJpaTest
class WareHouseServiceTest {

    @InjectMocks
    private WareHouseService warehouseService;

    @Mock
    private IWarehouseRepository warehouseRepository;

    @Test
    public void getQuantityProducts_returnTotalQuantity_WhenProductHaveQuantity(){

    }
}