package com.meli.desafio_final.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.Product;
import com.meli.desafio_final.model.SellerAd;
import com.meli.desafio_final.model.enums.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.meli.desafio_final.model.enums.Category.FROZEN;

public class TestUtilsGen_SellerAd {
    private static String SCOPE;
    private static ObjectWriter mapper;

    public static void emptyAdsFile() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile
                    ("./src/" + SCOPE + "/resources/seller_ads.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print("{}");
        writer.close();
    }

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

        return newProductList;
    }

    public static List<SellerAdDTO> getNewListAdDto(){
        return getNewListSellerAd().stream().
                map(SellerAdDTO::new).
                collect(Collectors.toList());
    }
}