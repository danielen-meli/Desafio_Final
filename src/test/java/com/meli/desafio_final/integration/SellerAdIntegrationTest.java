package com.meli.desafio_final.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SellerAdIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
      public void getListProducts_returnList() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products")
                        .contentType(MediaType.APPLICATION_JSON));

        mvcResult.andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(8)));
    }

    @Test
    public void getListProductsByCategory_returnList() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/category").
                        param("category", "FRESH")
                        .contentType(MediaType.APPLICATION_JSON));

        mvcResult.andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)));
    }
}
