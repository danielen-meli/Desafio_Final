package com.meli.desafio_final.controller.payment.service;

import com.meli.desafio_final.controller.payment.dto.CustomerDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.customer.CustomerClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.customer.Customer;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public ClientService() {
        MercadoPagoConfig.setAccessToken("TEST-3400945058736804-081210-23eb23642d6ccff5d60584729fd2488f-1006640405");
    }

    public Customer creatClient(CustomerDto customerDto) throws MPException, MPApiException {
        return new CustomerClient().create(customerDto.convert());
    }

    public Customer updateClient(String id, CustomerDto customerDto) throws MPException, MPApiException {
            new CustomerClient().update(id, customerDto.convert());
            return getClientById(id);
    }

    public Customer getClientById(String id) throws MPException, MPApiException {
       return new CustomerClient().get(id);
    }
}
