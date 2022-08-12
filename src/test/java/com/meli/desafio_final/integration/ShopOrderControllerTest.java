package com.meli.desafio_final.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafio_final.controller.ShopOrderController;
import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.OrderAdRequestDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.enums.Status;
import com.meli.desafio_final.repository.IShopOrderRepository;
import com.meli.desafio_final.service.IInboundService;
import com.meli.desafio_final.service.InboundService;
import com.meli.desafio_final.service.ShopOrderService;
import com.meli.desafio_final.util.TestUtilsGeneratorInboundOrder;
import com.meli.desafio_final.util.TestUtilsGeneratorShopOrder;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShopOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IShopOrderRepository shopOrderRepository;

    @Mock
    private ShopOrderService shopOrderService;

    @BeforeEach
    public void setup(){
        shopOrderRepository.deleteAll();
    }


//    @Test
//    public void insert_insertNewShopOrder_whenNewShoporder() throws Exception {
//        ShopOrderRequestDto shopOrder = TestUtilsGeneratorShopOrder.getShopOrderRequestDtoMock();
//
//        BDDMockito.when(shopOrderService.insertNewShopOrder(ArgumentMatchers.any(ShopOrderRequestDto.class)))
//                .thenReturn(shopOrder);
//
//        mockMvc.perform( post("/api/v1/fresh-products/orders")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }

    @Test
    public void getById_getShopOrder_WhenShopOrderIsValid() throws Exception{
        ShopOrder shopOrderSave = TestUtilsGeneratorShopOrder.newShopOrderToSave();

        BDDMockito.when(shopOrderService.getById(anyLong()))
                .thenReturn(shopOrderSave);

        mockMvc.perform( get("/api/v1/fresh-products/orders/1"))
                .andExpect(result -> {
                    System.out.println(result);
                })
                .andExpect(status().isOk());
    }




}