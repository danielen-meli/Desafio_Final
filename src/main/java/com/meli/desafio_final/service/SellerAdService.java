package com.meli.desafio_final.service;

import com.meli.desafio_final.dto.SellerAdDTO;
import com.meli.desafio_final.exception.NotFoundException;
import com.meli.desafio_final.model.enums.Category;
import com.meli.desafio_final.repository.ISellerAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerAdService implements ISellerAdService{

    @Autowired
    private ISellerAdRepository sellerAdRepo;

    /** Method that shows a list of all product ads registered.
     * @return list of product ads available
     */
    @Override
    public List<SellerAdDTO> getAllProducts() {
        List<SellerAdDTO> productsList = sellerAdRepo.findAll().
                stream().map(SellerAdDTO::new).
                collect(Collectors.toList());

        if(productsList.isEmpty()){
            throw new NotFoundException("A lista de produtos está vazia.");
        }

        return productsList;
    }

    /** Method that shows a list of all product ads registered ordered by category.
     * @param category FRESH, FROZEN or REFRIGERATED products
     * @return
     */
    @Override
    public List<SellerAdDTO> getByCategory(Category category){
        List<SellerAdDTO> productsByCat = sellerAdRepo.findAll().
                stream().map(SellerAdDTO::new).
                filter(p -> p.getProduct().getCategory().equals(category)).
                collect(Collectors.toList());

        if(productsByCat.isEmpty()){
            throw new NotFoundException("Não há produtos nesta categoria.");
        }

        return productsByCat;
    }
}
