package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.enums.Category;

import java.util.List;

public interface ISellerAdService {
    List<SellerAdDTO> getAllProducts();
    List<SellerAdDTO> getByCategory(Category category);
}
