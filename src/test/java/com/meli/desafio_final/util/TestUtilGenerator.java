package com.meli.desafio_final.util;

import com.meli.desafio_final.model.ShopOrder;
import com.meli.desafio_final.model.ShopOrderItem;
import com.meli.desafio_final.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class TestUtilGenerator {

    public static ShopOrder newShopOrderToSave() {
        ShopOrderItem item1 = new ShopOrderItem(1, null, 1.0, 1, null);
        List<ShopOrderItem> items = new ArrayList<>();
        items.add(item1);

        return ShopOrder.builder()
                .orderId(1)
                .status(Status.OPEN)
                .shopOrderItem(items)
                .build();
    }

}
