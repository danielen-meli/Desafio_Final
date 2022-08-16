package com.meli.desafio_final.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.repository.IInboundOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@Sql({"classpath:data.sql"})
public class InboundOrderIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    IInboundOrderRepository inboundOrderRepository;


    @Test
    public void InsertAndUpdateInboundOrder() throws Exception {
        InboundOrderRequestDto inboundOrderRequestDto = getInboundOrderRequestDtoMockIntegrationTests();
        InboundOrderRequestDto inboundOrderRequestDtoForUpdate = getInboundOrderRequestDtoMockUpdateIntegrationTests();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/fresh-products/inboundorder")
                .content(asJsonString(inboundOrderRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/fresh-products/inboundorder")
                .content(asJsonString(inboundOrderRequestDtoForUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static List<BatchStockRequestDto> generateBatchStockRequestDtoListMockIntegrationTests() {

        BatchStockRequestDto  batchStockRequestDto2 = BatchStockRequestDto
                .builder()
                .sellerAdId(2L)
                .currentTemperature(8.0)
                .minimumTemperature(1.0)
                .initialQuantity(150)
                .currentQuantity(150)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(150.0)
                .dueDate(LocalDate.of(2022, 10, 15))
                .build();

        BatchStockRequestDto  batchStockRequestDto3 = BatchStockRequestDto
                .builder()
                .sellerAdId(3L)
                .currentTemperature(5.0)
                .minimumTemperature(2.0)
                .initialQuantity(100)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(100.0)
                .dueDate(LocalDate.of(2022, 10, 15))
                .build();

        List<BatchStockRequestDto> batchStockRequestDtoList = new ArrayList<>();
        //batchStockRequestDtoList.add(batchStockRequestDto1);
        batchStockRequestDtoList.add(batchStockRequestDto2);
        batchStockRequestDtoList.add(batchStockRequestDto3);
        return batchStockRequestDtoList;
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoMockIntegrationTests(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListMockIntegrationTests();
        return InboundOrderRequestDto
                .builder()
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(1L)
                .batchStockList(batchStockRequestDtoList)
                .build();
    }

    public static List<BatchStockRequestDto> generateBatchStockRequestDtoListMockUpdateIntegrationTests() {

        BatchStockRequestDto  batchStockRequestDto2 = BatchStockRequestDto
                .builder()
                .batchStockId(1L)
                .sellerAdId(2L)
                .currentTemperature(8.0)
                .minimumTemperature(1.0)
                .initialQuantity(150)
                .currentQuantity(150)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(150.0)
                .dueDate(LocalDate.of(2022, 10, 15))
                .build();

        BatchStockRequestDto  batchStockRequestDto3 = BatchStockRequestDto
                .builder()
                .batchStockId(2L)
                .sellerAdId(3L)
                .currentTemperature(5.0)
                .minimumTemperature(2.0)
                .initialQuantity(100)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(100.0)
                .dueDate(LocalDate.of(2022, 10, 15))
                .build();

        List<BatchStockRequestDto> batchStockRequestDtoList = new ArrayList<>();
        //batchStockRequestDtoList.add(batchStockRequestDto1);
        batchStockRequestDtoList.add(batchStockRequestDto2);
        batchStockRequestDtoList.add(batchStockRequestDto3);
        return batchStockRequestDtoList;
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoMockUpdateIntegrationTests(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListMockUpdateIntegrationTests();
        return InboundOrderRequestDto
                .builder()
                .id(1L)
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(1L)
                .batchStockList(batchStockRequestDtoList)
                .build();
    }

}



