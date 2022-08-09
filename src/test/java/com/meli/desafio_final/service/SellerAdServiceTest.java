package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SellerAdServiceTest {

    @InjectMocks
    SellerAdService sellerAdService;

    @Mock
    ISellerAdRepository sellerAdRepository;

    @BeforeEach
    public void setup(){
        BDDMockito.when(sellerAdRepository.findAll())
                .thenReturn(TestUtilsGen_SellerAd.getNewListSellerAd());
    }

    @Test
    void getAllProductsWhenPresent() {
        List<SellerAdDTO> newListDto = TestUtilsGen_SellerAd.getNewListAdDto();

        sellerAdRepository.findAll();

        assertThat(sellerAdService.getAllProducts()).isEqualTo(newListDto);
    }

//    @Test
//    void getAllWhenNotPresent(){
//        List<SellerAdDTO> emptyList = null;
//
//        sellerAdRepository.findAll();
//
//        assertThrows(NotFoundException.class , () -> {sellerAdService.getAllProducts();});
//
//    }

    @Test
    void getByCategoryWhenPresent() {
        List<SellerAdDTO> listCtgFrozen = TestUtilsGen_SellerAd.getAdDtoCtgFrozen();
        List<SellerAdDTO> listCtgFresh = TestUtilsGen_SellerAd.getAdDtoCtgFresh();

        sellerAdRepository.findAll();

        assertThat(sellerAdService.getByCategory(Category.FROZEN)).isEqualTo(listCtgFrozen);
        assertThat(sellerAdService.getByCategory(Category.FRESH)).isEqualTo(listCtgFresh);
    }

    @Test
    void getByCategoryWhenNotPresent(){

        sellerAdRepository.findAll();

        NotFoundException message = assertThrows(NotFoundException.class ,
                () -> {sellerAdService.getByCategory(Category.REFRIGERATED);});

        assertEquals("Não há produtos nesta categoria.", message.getMessage());
    }
}