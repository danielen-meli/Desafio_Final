package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.util.TestUtilsGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellerAdServiceTest {

    @Test
    void getAllProducts() {
        List<SellerAdDTO> newList = TestUtilsGenerator.getNewListAds();

    }

    @Test
    void getByCategory() {
    }
}