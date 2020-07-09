package com.gca.checkoutservice.logic;

import com.gca.checkoutservice.data.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {
    List<CreditCardDto> getAllCreditCards();
    CreditCardDto createCreditCard(CreditCardDto dto) throws Exception;
    CreditCardDto getCreditCardByOrderId(Integer orderId);
}
