package com.meli.desafio_final.integration;

import com.meli.desafio_final.service.IInboundService;
import com.meli.desafio_final.util.TestUtilsGeneratorInboundOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WarehouseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    IInboundService inboundService;


    @Test
    public void testGetProductsInStock() throws Exception{
        inboundService.insertNewInboundOrder(TestUtilsGeneratorInboundOrder.getInboundOrderRequestDtoWithWrongWarehouseMockIntegration());

        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse/2/2"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));

    }




}
