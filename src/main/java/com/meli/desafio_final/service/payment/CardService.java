package com.meli.desafio_final.service.payment;

import com.meli.desafio_final.dto.payment.CustomerCardDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.customer.CustomerCardClient;
import com.mercadopago.client.customer.CustomerCardCreateRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.customer.CustomerCard;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    public CardService(){
        MercadoPagoConfig.setAccessToken("TEST-3400945058736804-081210-23eb23642d6ccff5d60584729fd2488f-1006640405");
    }

    public CustomerCard create(String customerId, CustomerCardDto customerDto) throws MPException, MPApiException {

        CustomerCardClient client = new CustomerCardClient();
            CustomerCardCreateRequest convert = customerDto.convert();
            CustomerCard customerCard = client.create(customerId, convert);
            return customerCard;
    }

    public CustomerCard delete(String customerId, String cardId) throws MPException, MPApiException {
        return new CustomerCardClient().delete(customerId, cardId);
    }

    public MPResourceList<CustomerCard> getAllByCustomerId(String customerId) throws MPException, MPApiException {
        return new CustomerCardClient().listAll(customerId);
    }
}
