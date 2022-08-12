
package com.meli.desafio_final.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BatchStockIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    //@Sql({"data.sql"})

    @Test
    public void testGetProductsInStock() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/stock/")
                        .param("productId", "jjnjb"))
                        .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void tesGetProductsInStockOrdered_whenParamIsInvalid() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/stock/orderBy")
                        .param("productId", "2")
                        .param("orderBy", "asc"))
                        .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetBatchStocksByDueDate() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/due-date")
                        .param("number_days", "100")
                        .param("section", "1"))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));
    }

    @Test
    public void tesGgetBachStocksFilteredBy() throws Exception{
        ResultActions mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/due-date/list")
                        .param("number_days", "100")
                        .param("category", "REFRIGERATED")
                        .param("orderType", "asc"))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));
    }


}
