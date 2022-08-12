package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.BatchStockRequestDto;
import com.meli.desafio_final.dto.InboundOrderRequestDto;
import com.meli.desafio_final.model.*;
import com.meli.desafio_final.model.enums.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestUtilsGeneratorInboundOrder {

    public static List<BatchStockRequestDto> generateBatchStockRequestDtoListMock() {
        BatchStockRequestDto  batchStockRequestDto1 = BatchStockRequestDto
                .builder()
                .sellerAdId(1L)
                .currentTemperature(5.0)
                .minimumTemperature(2.0)
                .initialQuantity(100)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(100.0)
                .dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(1))
                .build();

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
                .dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(1))
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
        batchStockRequestDtoList.add(batchStockRequestDto1);
        batchStockRequestDtoList.add(batchStockRequestDto2);
        batchStockRequestDtoList.add(batchStockRequestDto3);
        return batchStockRequestDtoList;
    }

    public static List<BatchStockRequestDto> generateBatchStockRequestDtoListUpdateMock() {
        BatchStockRequestDto  batchStockRequestDto1 = BatchStockRequestDto
                .builder()
                .batchStockId(1)
                .sellerAdId(1L)
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
        batchStockRequestDtoList.add(batchStockRequestDto1);
        return batchStockRequestDtoList;
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoMock(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListMock();
        return InboundOrderRequestDto
                .builder()
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(1L)
                .batchStockList(batchStockRequestDtoList)
                .build();
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoUpdateMock(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListUpdateMock();
        return InboundOrderRequestDto
                .builder()
                .id(1)
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(1L)
                .batchStockList(batchStockRequestDtoList)
                .build();
    }


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

    public static Seller getSellerMock() {
        return Seller
                .builder()
                .sellerId(1)
                .sellerName("Seller01")
                .user(getUserMock())
                .build();
    }


    public static Product getProductMock(long productId, String productName, Category category) {
        return Product
                .builder()
                .productId(productId)
                .productName(productName)
                .category(category)
                .build();
    }

    public static Optional<SellerAd> getSellerAdMock() {
        Product product01 = getProductMock(1, "maça", Category.REFRIGERATED);

        SellerAd sellerAd = SellerAd.builder()
                .sellerAdId(1)
                .price(5)
                .seller(getSellerMock())
                .product(product01)
                .build();

        return Optional.ofNullable(sellerAd);
    }

    public static BatchStock getBatchStockMock() {
        return BatchStock.builder()
                .batchStockId(1)
                .sellerAd(getSellerAdMock().get()) // criar outro com id 2
                .currentTemperature(5)
                .minimumTemperature(2)
                .initialQuantity(100)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(100)
                .dueDate(LocalDate.of(2022, 10, 15))
                .inboundOrder(getInboundOrderMock().get())
                .build();
    }

    public static List<BatchStock> getBatchStockListMock() {


        List<BatchStock> batchStockList = new ArrayList<>();

        BatchStock batchStock01 = BatchStock.builder()
                .batchStockId(1)
                .sellerAd(getSellerAdMock().get()) // criar outro com id 2
                .currentTemperature(5)
                .minimumTemperature(2)
                .initialQuantity(100)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(100)
                .dueDate(LocalDate.of(2022, 10, 15))
                .inboundOrder(getInboundOrderMock().get())
                .build();

        batchStockList.add(batchStock01);

        return batchStockList;
    }

    public static Optional<InboundOrder> getInboundOrderMock() {
        InboundOrder inboundOrder = InboundOrder
                .builder()
                .inboundOrderId(1)
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(getSectionMock().get())
                .build();
        return Optional.ofNullable(inboundOrder);
    }

    public static Optional<Warehouse> getWharehouseMock() {
        return Optional.ofNullable(Warehouse
                .builder()
                .warehouseId(1)
                .name("Warehouse01")
                .localization("SP")
                .build());
    }

    public static Optional<Section> getSectionMock() {
        Section section = Section
                .builder()
                .sectionId(1)
                .warehouse(getWharehouseMock().get())
                .sectionTemperature(10)
                .sectionCapacity(500)
                .category(Category.REFRIGERATED)
                .build();
        return Optional.ofNullable(section);
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoWithWrongSectionMock(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListMock();
        return InboundOrderRequestDto
                .builder()
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(2L)
                .batchStockList(batchStockRequestDtoList)
                .build();
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoWithWrongWarehouseMock(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListMock();
        return InboundOrderRequestDto
                .builder()
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(1L)
                .batchStockList(batchStockRequestDtoList)
                .build();
    }

    public static Warehouse getWharehouseWrongIdMock() {
        return Warehouse
                .builder()
                .warehouseId(2)
                .name("Warehouse02")
                .localization("SP")
                .build();
    }

    public static Section getSectionWithWrongWarehouseMock() {
        return Section
                .builder()
                .sectionId(1)
                .warehouse(getWharehouseWrongIdMock())
                .sectionTemperature(10)
                .sectionCapacity(500)
                .category(Category.REFRIGERATED)
                .build();
    }

    // mocks test integração
    public static List<BatchStockRequestDto> generateBatchStockRequestDtoListMockIntegration() {
        BatchStockRequestDto  batchStockRequestDto1 = BatchStockRequestDto
                .builder()
                .sellerAdId(2L)
                .currentTemperature(5.0)
                .minimumTemperature(2.0)
                .initialQuantity(100)
                .currentQuantity(100)
                .manufacturingDate(LocalDate.of(2022, 8, 8))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(100.0)
                .dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(1))
                .build();

        BatchStockRequestDto  batchStockRequestDto2 = BatchStockRequestDto
                .builder()
                .sellerAdId(3L)
                .currentTemperature(8.0)
                .minimumTemperature(1.0)
                .initialQuantity(50)
                .currentQuantity(50)
                .manufacturingDate(LocalDate.of(2022, 8, 10))
                .manufacturingTime(LocalDateTime.of(2022, 5, 15, 12,45))
                .volume(50.0)
                .dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(1))
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
                .dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(1))
                .build();

        List<BatchStockRequestDto> batchStockRequestDtoList = new ArrayList<>();
        batchStockRequestDtoList.add(batchStockRequestDto1);
        batchStockRequestDtoList.add(batchStockRequestDto2);
        batchStockRequestDtoList.add(batchStockRequestDto3);
        return batchStockRequestDtoList;
    }

    public static InboundOrderRequestDto getInboundOrderRequestDtoWithWrongWarehouseMockIntegration(){
        List<BatchStockRequestDto> batchStockRequestDtoList = generateBatchStockRequestDtoListMock();
        return InboundOrderRequestDto
                .builder()
                .orderDate(LocalDate.of(2022, 8, 8))
                .section(1L)
                .batchStockList(generateBatchStockRequestDtoListMockIntegration())
                .build();
    }
}

