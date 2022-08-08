package com.meli.desafio_final.controller.dto;

import com.meli.desafio_final.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerAdDto {
    private Product product;
}
