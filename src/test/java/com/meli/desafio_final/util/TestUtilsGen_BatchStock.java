package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.model.*;
import com.meli.desafio_final.model.enums.Category;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestUtilsGen_BatchStock {
    public static List<BatchStock> getNewListBatchStock(){
        List<BatchStock> listBatchStock = new ArrayList<>();

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(1);

        Section section = new Section();
        section.setSectionId(1);
        section.setCategory(Category.REFRIGERATED);
        section.setWarehouse(warehouse);


        Product product = new Product();
        product.setProductId(1);
        product.setCategory(Category.REFRIGERATED);

        SellerAd sellerAd = new SellerAd();
        sellerAd.setProduct(product);

        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setSection(section);

        listBatchStock.add(BatchStock.builder().
                batchStockId(1).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(10).
                dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(1))
                .build());

        listBatchStock.add(BatchStock.builder().
                batchStockId(2).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(15).
                dueDate(LocalDate.now().plusDays(1).plusMonths(1).plusYears(0))
                .build());

        listBatchStock.add(BatchStock.builder().
                batchStockId(3).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(20).
                dueDate(LocalDate.now())
                .build());

        listBatchStock.add(BatchStock.builder().
                batchStockId(1).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(10).
                dueDate(LocalDate.now().plusDays(1).plusMonths(2))
                .build());

        return listBatchStock;
    }


    public static List<BatchStockDto> getNewListBStockDto(){
        return getNewListBatchStock().stream().map(BatchStockDto::new).collect(Collectors.toList());
    }

    public static List<BatchStockDto> getNewListOrderedByStockId() {
        return getNewListBStockDto().stream().
                sorted(Comparator.comparingLong(BatchStockDto::getBatchStockId)).
                collect(Collectors.toList());
    }

    public static List<BatchStockDto> getNewListOrderedByQuantity() {
        return getNewListBStockDto().stream().
                sorted(Comparator.comparingLong(BatchStockDto::getCurrentQuantity)).
                collect(Collectors.toList());
    }

    public static List<BatchStockDto> getNewListOrderedByDueDate() {
        return getNewListBStockDto().stream().
                sorted(Comparator.comparing(BatchStockDto::getDueDate)).
                collect(Collectors.toList());
    }

    // todo: teste dos metodos getBatchStocksByDueDate - getBatchStocksFilteredBy

    //    public List<BatchStockByDueDateResponseDto>
    //    (int numberOfDays, Category category, String orderType) {


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

    public static List<BigInteger> getSectionsIdsByCategory() {
        List<BigInteger> idsSectionsList = new ArrayList<>();
        idsSectionsList.add(BigInteger.valueOf(1));
        return idsSectionsList;
    }


    public static Optional<InboundOrder> getInboundOrderMock() {
        InboundOrder inboundOrder = InboundOrder
                .builder()
                .inboundOrderId(1)
                .section(getSectionMock().get())
                .batchStockList(getNewListBatchStock())
                .build();
        return Optional.ofNullable(inboundOrder);
    }

    public static List<InboundOrder> getInboundOrderBySectionsMock() {
        List<InboundOrder> inboundOrderList = new ArrayList();
        inboundOrderList.add(getInboundOrderMock().get());
        return inboundOrderList;
    }

}
