package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDto;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.ISellerAdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerAdService {

    @Autowired
    private ISellerAdRepo sellerAdRepo;

    public List<SellerAdDto> getAllProducts() {
        return sellerAdRepo.findAll().
                stream().map(SellerAdDto::new).
                collect(Collectors.toList());
    }

    public List<SellerAdDto> getByCategory(Category category){
        List<SellerAdDto> allProducts = getAllProducts();

        return allProducts.stream().
                filter(p -> p.getProduct().getCategory().equals(category)).
                collect(Collectors.toList());
    }
}
