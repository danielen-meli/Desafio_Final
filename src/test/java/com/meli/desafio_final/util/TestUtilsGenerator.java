package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.BatchStock;
import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;

import java.util.ArrayList;
import java.util.List;

import static com.meli.desafio_final.model.enums.Category.FROZEN;

public class TestUtilsGenerator {

    public static List<SellerAdDTO> getNewListAds(){
        List<SellerAdDTO> newProductList = new ArrayList<>();

        SellerAd newSellerAd = new SellerAd();
        newSellerAd.setPrice(15.00);

        List<BatchStock> newBatchList = new ArrayList<>();
        newBatchList.add(new BatchStock());
        newBatchList.add(new BatchStock());
        newSellerAd.setBatchStockId(newBatchList);

        Product newProduct = new Product(1, "Frango", FROZEN);
        newSellerAd.setProduct(newProduct);

        SellerAdDTO newSellerAdDto = new SellerAdDTO(newSellerAd);

        newProductList.add(newSellerAdDto);

        return newProductList;
    }
}
