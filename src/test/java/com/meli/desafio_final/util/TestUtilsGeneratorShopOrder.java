package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.OrderAdRequestDto;
import com.meli.desafio_final.dto.ShopOrderRequestDto;
import com.meli.desafio_final.model.*;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.model.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                .orderId(1)
                .status(Status.OPEN)
                .shopOrderItem(Arrays.asList(shopOrderItem))
                .build();
    }

    public static ShopOrder newShopOrderWithTwoBatch() {
        Product produto = Product.builder()
                .productName("bicicleta")
                .build();

        BatchStock batchStock = BatchStock.builder().currentQuantity(100).build();
        BatchStock batchStockDois = BatchStock.builder().currentQuantity(80).build();

        SellerAd sellerAd = SellerAd.builder()
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

    public static User getUserMock() {
        return User
                .builder()
                .userId(1)
                .userName("User01")
                .email("user01@email.com")
                .password("pass")
                .cpf("067.987.543-27")
                .build();
    }

    public static Buyer generateBuyer(){
        return Buyer.builder()
                .buyerId(1)
                .user(getUserMock())
                .build();
    }

    public static SellerAd genarateSellerAd (){
        return SellerAd.builder()
                .sellerAdId(2)
                .product(new Product(1, "banana", Category.REFRIGERATED))
                .batchStockId(Arrays.asList(null))
                .build();
    }

    public static List<BatchStock> genarateBatchStock (){
        List<BatchStock> batchStockList = new ArrayList<>();

        batchStockList.add(BatchStock.builder()
                .batchStockId(1L)
                .sellerAd(genarateSellerAd())
                .currentTemperature(17.0)
                .minimumTemperature(10.0)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .volume(1)
                .dueDate(LocalDate.of(LocalDate.now().plusDays(1).plusMonths(1).plusYears(0)))
                .build());
    }
}
