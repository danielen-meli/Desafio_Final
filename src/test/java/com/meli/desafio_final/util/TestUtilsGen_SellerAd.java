package com.meli.desafio_final.util;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.meli.desafio_final.model.enums.Category.*;

public class TestUtilsGen_SellerAd {

    public static List<SellerAd> getNewListSellerAd(){
        List<SellerAd> newProductList = new ArrayList<>();
        Product newProduct_1 = new Product(1, "Frango", FROZEN);
        Product newProduct_2 = new Product(2, "Tomate", Category.FRESH);

        newProductList.add(SellerAd.builder().
                price(15.00).
                product(newProduct_1).build());

        newProductList.add(SellerAd.builder().
                price(5.00).
                product(newProduct_2).build());

        return newProductList;//lista com dois anuncios de produto, nenhum da categoria REFRIGERATED
    }

    public static List<SellerAdDTO> getNewListAdDto(){
        return getNewListSellerAd().stream().
                map(SellerAdDTO::new).
                collect(Collectors.toList());
    }

    public static List<SellerAdDTO> getAdDtoCtgFrozen(){
        return getNewListSellerAd().stream().
                filter(p -> p.getProduct().getCategory().equals(FROZEN)).
                map(SellerAdDTO::new).
                collect(Collectors.toList());
    }

    public static List<SellerAdDTO> getAdDtoCtgFresh(){
        return getNewListSellerAd().stream().
                filter(p -> p.getProduct().getCategory().equals(FRESH)).
                map(SellerAdDTO::new).
                collect(Collectors.toList());
    }
}
