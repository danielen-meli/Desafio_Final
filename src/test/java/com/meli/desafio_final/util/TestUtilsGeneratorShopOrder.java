package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.dto.OrderAdRequestDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.model.*;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.model.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestUtilsGeneratorShopOrder {

    public static ShopOrder newShopOrderToSave() {
        Product produto = Product.builder()
                .productName("bicicleta")
                .build();

        BatchStock batchStock = BatchStock.builder().currentQuantity(100).build();

        SellerAd sellerAd = SellerAd.builder()
                .sellerAdId(1L)
                .product(produto)
                .batchStockId(Arrays.asList(batchStock))
                .build();

        ShopOrderItem shopOrderItem = ShopOrderItem.builder()
                .id(1)
                .price(20.0)
                .quantity(5)
                .sellerAd(sellerAd)
                .build();

        return ShopOrder.builder()
                .orderId(2)
                .status(Status.OPEN)
                .shopOrderItem(Arrays.asList(shopOrderItem))
                .build();
    }

    public static ShopOrder newShopOrderWithTwoBatch() {
        Product produto = Product.builder()
                .productName("picanha")
                .build();

        BatchStock batchStock = BatchStock.builder().currentQuantity(100).build();
        BatchStock batchStockDois = BatchStock.builder().currentQuantity(80).build();

        SellerAd sellerAd = SellerAd.builder()
                .sellerAdId(1L)
                .product(produto)
                .batchStockId(Arrays.asList(batchStock, batchStockDois))
                .build();

        SellerAd sellerAd2 = SellerAd.builder()
                .sellerAdId(1L)
                .product(produto)
                .batchStockId(Arrays.asList(batchStock, batchStockDois))
                .build();

        ShopOrderItem shopOrderItem = ShopOrderItem.builder()
                .id(1)
                .price(20.0)
                .quantity(175)
                .sellerAd(sellerAd)
                .build();

        return ShopOrder.builder()
                .orderId(1)
                .status(Status.OPEN)
                .shopOrderItem(Arrays.asList(shopOrderItem))
                .build();
    }

    public static List<OrderAdRequestDto> generatedOrderAdRequestDtoListMock(){
        OrderAdRequestDto orderAdRequestDto1 = OrderAdRequestDto
                .builder()
                .sellerAdId(1L)
                .quantity(50)
                .build();

        List<OrderAdRequestDto> orderAdRequestDtoList = new ArrayList<>();
        orderAdRequestDtoList.add(orderAdRequestDto1);
        return orderAdRequestDtoList;
    }

    public static ShopOrderRequestDto getShopOrderRequestDtoMock(){
        List<OrderAdRequestDto> orderAdRequestDtosList = generatedOrderAdRequestDtoListMock();
        return ShopOrderRequestDto
                .builder()
                .date(LocalDate.of(2022,07,8))
                .buyerId(1L)
                .orderStatus(Status.OPEN)
                .products(orderAdRequestDtosList)
                .build();
    }

    public static ShopOrder getShopOrderMock(){
        Product produto = Product
                .builder()
                .productName("Coberta")
                .build();

        BatchStock batchStock = BatchStock.builder().currentQuantity(100).build();

        SellerAd sellerAd = SellerAd.builder()
                .sellerAdId(2L)
                .product(produto)
                .batchStockId(Arrays.asList(batchStock))
                .build();

        ShopOrderItem shopOrderItem = ShopOrderItem.builder()
                .id(1)
                .price(45.0)
                .quantity(7)
                .sellerAd(sellerAd)
                .build();

        return ShopOrder.builder()
                .orderId(1)
                .status(Status.OPEN)
                .shopOrderItem(Arrays.asList(shopOrderItem))
                .build();
    }

    //Mocks tati e adão

    public static Buyer generatedBuyer(){
        return Buyer.builder()
                .buyerId(1)
                .user(TestUtilsGeneratorInboundOrder.getUserMock())
                .build();
    }


    public static ShopOrder newShopOrderReq6(){
        SellerAd sellerAd = SellerAd.builder()
                .seller(null)
                .product(Product.builder()
                        .productName("picanha")
                        .category(Category.FRESH)
                        .build())
                .build();

        List<ShopOrderItem> shopOrderItems = new ArrayList<>();
        shopOrderItems.add(ShopOrderItem.builder()
                .sellerAd(sellerAd)
                .price(120)
                .quantity(1)
                .build());

        return ShopOrder.builder()
                .orderId(1)
                .shopOrderItem(shopOrderItems)
                .status(Status.OPEN)
                .build();
    }

    public static InboundOrderRequestDto inboundOrderRequestDtoReq6(){
        List<BatchStockRequestDto> list = new ArrayList<>();

                list.add(BatchStockRequestDto.builder()
                        .sellerAdId(7L)
                        .currentTemperature(8.0)
                        .minimumTemperature(1.0)
                        .initialQuantity(10)
                        .manufacturingDate(LocalDate.now())
                        .manufacturingTime(LocalDateTime.now())
                        .volume(50.0)
                        .dueDate(LocalDate.now().plusMonths(2))
                        .build());

        return InboundOrderRequestDto.builder()
                .orderDate(LocalDate.now())
                .section(3L)
                .batchStockList(list)
                .build();
    }

    public static ShopOrderRequestDto shopOrderRequestDtoReq6(){
        List<OrderAdRequestDto> orderAdRequestDtoList = new ArrayList<>();

        orderAdRequestDtoList.add(OrderAdRequestDto.builder()
                .sellerAdId(7L)
                .quantity(1)
                .build());

        return ShopOrderRequestDto.builder()
                .buyerId(1L)
                .date(LocalDate.now())
                .orderStatus(Status.OPEN)
                .products(orderAdRequestDtoList)
                .build();
    }
}
