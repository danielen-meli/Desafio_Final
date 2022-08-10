package com.meli.desafio_final.util;

import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.InboundOrder;
import com.meli.desafio_final.model.Section;
import com.meli.desafio_final.model.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.meli.desafio_final.model.enums.Category.FROZEN;

public class TestUtilGeneratorWareHouse {

    public static Section newSectionToWareHouse(){
        Warehouse warehouse = Warehouse.builder()
                .localization("SP")
                .name("warehouse")
                .warehouseId(1)
                .build();

        BatchStock batchStock = BatchStock.builder().currentQuantity(100).build();

        InboundOrder inboundOrder = InboundOrder.builder()
                .orderDate(LocalDate.now())
                .inboundOrderId(1)
                .batchStockList(Arrays.asList(batchStock))
                .build();

        return Section.builder()
                .category(FROZEN)
                .sectionCapacity(2.0)
                .sectionId(1)
                .warehouse(warehouse)
                .inboundOrder(Arrays.asList(inboundOrder))
                .build();

    }
}
