package com.gca.cartservice.logic;

import com.gca.cartservice.data.dto.OrderDto;
import com.gca.cartservice.data.entities.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();
    List<OrderDto> getAllBySessionId(String sessionId);
    OrderDto addOrder(OrderDto orderDto, String sessionId);
    boolean delete(Integer id, String sessionId);
    boolean deleteAllBySessionId(String sessionId);
}
