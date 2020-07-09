package com.gca.checkoutservice.logic;

import com.gca.checkoutservice.data.dto.CreditCardDto;
import com.gca.checkoutservice.data.dto.DeliveryInformationDto;
import com.gca.checkoutservice.data.dto.OrderDto;
import com.gca.checkoutservice.data.dto.OrderItemDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto createOrder(OrderDto dto,
                         CreditCardDto creditCardDto,
                         DeliveryInformationDto deliveryInformationDto,
                         List<OrderItemDto> orderItemDtos);
}
