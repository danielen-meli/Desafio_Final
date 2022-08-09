package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
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
    void getAllProducts() {
        List<SellerAd> newListSellerAd = TestUtilsGen_SellerAd.getNewListSellerAd();
        List<SellerAdDTO> newListDto = TestUtilsGen_SellerAd.getNewListAdDto();

        sellerAdRepository.saveAll(newListSellerAd);

        assertThat(sellerAdService.getAllProducts()).isEqualTo(newListDto);
    }

    @Test
    void getByCategory() {
        List<SellerAd> newListSellerAd = TestUtilsGen_SellerAd.getNewListSellerAd();
        List<SellerAdDTO> listCtgFrozen = TestUtilsGen_SellerAd.getAdDtoCtgFrozen();
        List<SellerAdDTO> listCtgRefrig = TestUtilsGen_SellerAd.getAdDtoCtgRefrig();
        List<SellerAdDTO> listCtgFresh = TestUtilsGen_SellerAd.getAdDtoCtgFresh();

        sellerAdRepository.saveAll(newListSellerAd);

        assertThat(sellerAdService.getByCategory(Category.FROZEN)).isEqualTo(listCtgFrozen);
        assertThat(sellerAdService.getByCategory(Category.REFRIGERATED)).isEqualTo(listCtgRefrig);
        assertThat(sellerAdService.getByCategory(Category.FRESH)).isEqualTo(listCtgFresh);
    }
}