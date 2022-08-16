package com.meli.desafio_final.model;

public interface ISellerHistoryByProduct {
    Long getId();
    Double getTotal_quantity_sold();
    Long getSeller_seller_id();
    String getSeller_name();
    Double getPrice();
    String getEmail();
    String getProduct_name();
}
