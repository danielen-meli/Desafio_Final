package com.meli.desafio_final.service;

import com.meli.desafio_final.model.PromoCode;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.repository.IPromoCodeRepository;
import com.meli.desafio_final.util.TestUtilsGen_PromoCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PromoCodeServiceTest {

    @InjectMocks
    PromoCodeService promoCodeService;

    @Mock
    IPromoCodeRepository promoCodeRepository;

    @Test
    void registerNewPromo() {
        PromoCode savedPromoCode = TestUtilsGen_PromoCode.newPromoCode();

        BDDMockito.when(promoCodeRepository.save(ArgumentMatchers.any(PromoCode.class)))
                .thenReturn(savedPromoCode);

        PromoCode promoCodeTest = promoCodeService.registerNewPromo(savedPromoCode);

        assertThat(promoCodeTest).isEqualTo(savedPromoCode);
    }
}