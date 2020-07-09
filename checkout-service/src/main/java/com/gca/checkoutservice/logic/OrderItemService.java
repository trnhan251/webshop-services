package com.gca.checkoutservice.logic;

import com.gca.checkoutservice.data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAllOrderItemsByOrderId(Integer orderId);
    OrderItemDto addOrderItem(OrderItemDto dto);
    List<OrderItemDto> addOrderIntoOrderItems(Integer orderId, List<Integer> orderItemIds);
}
