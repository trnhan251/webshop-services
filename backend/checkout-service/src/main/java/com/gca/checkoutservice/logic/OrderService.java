package com.gca.checkoutservice.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gca.checkoutservice.data.dto.*;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    ShippingOrderDto createOrder(OrderDto dto) throws JsonProcessingException;
}
