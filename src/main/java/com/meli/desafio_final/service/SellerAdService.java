package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.ISellerAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerAdService {

    @Autowired
    private ISellerAdRepository sellerAdRepo;

    public List<SellerAdDTO> getAllProducts() {
        return sellerAdRepo.findAll().
                stream().map(SellerAdDTO::new).
                collect(Collectors.toList());
    }

    public List<SellerAdDTO> getByCategory(Category category){
        List<SellerAdDTO> allProducts = getAllProducts();

        return allProducts.stream().
                filter(p -> p.getProduct().getCategory().equals(category)).
                collect(Collectors.toList());
    }
}
