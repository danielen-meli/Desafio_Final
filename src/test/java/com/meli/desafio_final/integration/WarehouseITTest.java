package com.meli.desafio_final.integration;

import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.Warehouse;
import com.meli.desafio_final.repository.ISellerAdRepository;
import com.meli.desafio_final.repository.IWarehouseRepository;
import com.meli.desafio_final.service.IInboundService;
import com.meli.desafio_final.util.TestUtilsGen_SellerAd;
import com.meli.desafio_final.util.TestUtilsGeneratorInboundOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class WarehouseITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    IInboundService inboundService;


    @Test
    public void testGetProductsInStock() throws Exception{
        inboundService.insertNewInboundOrder(TestUtilsGeneratorInboundOrder.getInboundOrderRequestDtoWithWrongWarehouseMockInt());
//        ResultActions mvcResult =
//                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse")
//                        .param("sellerAdId", "3")
//                        .param("wareHouseId", "2"))
//                        .andDo(print()).andExpect(status().isOk())
//                        .andExpect(content().contentType("application/json"));

    }


}
