package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.BatchStockDto;
import com.meli.desafio_final.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtilsGen_BatchStock {
    public static List<BatchStock> getNewListBatchStock(){
        List<BatchStock> listBatchStock = new ArrayList<>();

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(1);

        Section section = new Section();
        section.setSectionId(1);
        section.setWarehouse(warehouse);

        Product product = new Product();
        product.setProductId(1);

        SellerAd sellerAd = new SellerAd();
        sellerAd.setProduct(product);

        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setSection(section);

        listBatchStock.add(BatchStock.builder().
                batchStockId(1).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(10).
                dueDate(LocalDate.of(2022, 8, 30))
                .build());

        listBatchStock.add(BatchStock.builder().
                batchStockId(2).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(15).
                dueDate(LocalDate.of(2022, 11, 30)).
                build());

        listBatchStock.add(BatchStock.builder().
                batchStockId(3).sellerAd(sellerAd).
                inboundOrder(inboundOrder).currentQuantity(20).
                dueDate(LocalDate.of(2022, 10, 30))
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
}
