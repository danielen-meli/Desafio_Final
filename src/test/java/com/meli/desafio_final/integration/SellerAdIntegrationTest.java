package com.meli.desafio_final.integration;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellerAdIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void getListProducts_returnList(){
        List<SellerAdDTO> newListDto = TestUtilsGen_SellerAd.getNewListAdDto();
        String baseUrl = "http://localhost:" + port + "/student/getStudent/";

    }

    @Test
    public void getListProducts_returnNotFound(){

    }
}
