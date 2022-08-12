package com.meli.desafio_final.service;


import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.Warehouse;
import com.meli.desafio_final.repository.IWarehouseRepository;
import com.meli.desafio_final.util.TestUtilGeneratorWareHouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WareHouseServiceTest {

    @InjectMocks
    private WareHouseService warehouseService;

    @Mock
    private IWarehouseRepository warehouseRepository;

    @Test
    public void getQuantityProducts_returnTotalQuantity_WhenProductHaveQuantity(){

        Section section = TestUtilGeneratorWareHouse.newSectionToWareHouse();
        SellerAd sellerAd = SellerAd.builder()
                .sellerAdId(1)
                .build();


        BDDMockito.when(warehouseRepository.getAllProducts(anyLong(), anyLong())).thenReturn(100l);

        long warehouseFind = warehouseService.getTotalQuantitySellerAdinWareHouse(sellerAd, section.getWarehouse());

        assertThat(warehouseFind).isEqualTo(100l);

    }

    @Test
    public void verifyProduct_throwException_WhenProductNotHaveQuantity(){
        Section section = TestUtilGeneratorWareHouse.newSectionToWareHouse();
        SellerAd sellerAd = SellerAd.builder()
                .sellerAdId(1)
                .build();

        BDDMockito.when(warehouseRepository.getAllProducts(anyLong(), anyLong())).thenReturn(0l);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            warehouseService.getTotalQuantitySellerAdinWareHouse(sellerAd, section.getWarehouse());
        });

        assertThat(exception.getMessage().contains("Produto não existe em nenhum depósito"));
        assertThat(exception.getClass()).isEqualTo(NotFoundException.class);

    }
}
