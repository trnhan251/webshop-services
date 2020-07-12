package com.gca.cartservice.logic.impl;

import com.gca.cartservice.data.dto.OrderDto;
import com.gca.cartservice.data.entities.Order;
import com.gca.cartservice.data.repo.OrderRepository;
import com.gca.cartservice.logic.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDto> getAll() {
        return this.repository.findAll()
                .stream()
                .map(this::getMappedOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllBySessionId(String sessionId) {
        return this.repository.findBySessionId(sessionId)
                .stream()
                .map(this::getMappedOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto, String sessionId) {
        if (orderDto == null || orderDto.getProductId() < 1 || orderDto.getQuantity() < 1)
            return null;

        Order order = repository.findOrderByProductIdAndSessionId(orderDto.getProductId(), sessionId);

        if (order == null)
            order = new Order()
                .setSessionId(sessionId)
                .setProductId(orderDto.getProductId())
                .setQuantity(orderDto.getQuantity());
        else
            order.setQuantity(orderDto.getQuantity());

        Order savedOrder = repository.save(order);
        return getMappedOrderDto(savedOrder);
    }

    @Override
    public boolean delete(Integer id, String sessionId) {
        Order order = repository.getOne(id);
        if (!order.getSessionId().equalsIgnoreCase(sessionId)) return false;
        repository.delete(order);
        return true;
    }

    @Override
    public boolean deleteAllBySessionId(String sessionId) {
        repository.deleteBySessionId(sessionId);
        return true;
    }

    private OrderDto getMappedOrderDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        return orderDto;
    }
}
