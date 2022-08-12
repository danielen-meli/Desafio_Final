package com.meli.desafio_final.integration;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql({"classpath:data.sql"})
public class SellerAdIntegrationTest {

      @Autowired
      public MockMvc mockMvc;

//    @Autowired
//    TestRestTemplate testRestTemplate;
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    ISellerAdRepository sellerAdRepository;

    @Test
      public void getListProducts_returnList() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products")
                        .contentType(MediaType.APPLICATION_JSON));

        mvcResult.andExpect(status().isOk());
    }

//    @Test
//    public void getListProducts_returnList(){
//        List<SellerAd> newListSellerAd = TestUtilsGen_SellerAd.getNewListSellerAd();
//        sellerAdRepository.saveAll(newListSellerAd);
//        String baseUrl = "http://localhost:" + port + "/api/v1/fresh-products";
//
//        ResponseEntity<SellerAdDTO> result = testRestTemplate.exchange(baseUrl,
//                HttpMethod.GET, null, SellerAdDTO.class);
//
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }//TODO nao funciona.
//
//    @Test
//    public void getListProducts_returnNotFound(){
//        sellerAdRepository.saveAll(new ArrayList<>());
//        String baseUrl = "http://localhost:" + port + "/api/v1/fresh-products";
//
//        ResponseEntity<SellerAdDTO> result = testRestTemplate.exchange(baseUrl,
//                HttpMethod.GET, null, SellerAdDTO.class);
//
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
}
