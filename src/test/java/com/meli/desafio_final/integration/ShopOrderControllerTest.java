package com.meli.desafio_final.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.desafio_final.controller.ShopOrderController;
import com.meli.desafio_final.dto.*;
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

    @Autowired
    private IInboundService inboundService;

    @Autowired
    private ShopOrderService shopOrderS;

    @BeforeEach
    public void setup(){
        shopOrderRepository.deleteAll();
    }



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
//
//    @Test
//    public void getDiscount_ShopOrder() throws Exception{
//
//        InboundOrderResponseDto inboundOrderResponseDto = inboundService.insertNewInboundOrder(TestUtilsGeneratorShopOrder.inboundOrderRequestDtoReq6());
//        ShopOrderResponseDto shop = shopOrderS.insertNewShopOrder(TestUtilsGeneratorShopOrder.shopOrderRequestDtoReq6());
//
//        mockMvc.perform( get("/api/v1/fresh-products/orders/discount/3"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"));
//    }



}