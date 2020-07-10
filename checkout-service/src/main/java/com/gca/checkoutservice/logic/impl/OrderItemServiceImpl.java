package com.gca.checkoutservice.logic.impl;

import com.gca.checkoutservice.data.dto.OrderItemDto;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.entities.OrderItem;
import com.gca.checkoutservice.data.repo.OrderItemRepository;
import com.gca.checkoutservice.data.repo.OrderRepository;
import com.gca.checkoutservice.logic.OrderItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    private final ModelMapper modelMapper;
    private final OrderItemRepository repository;
    private final OrderRepository orderRepository;

    public OrderItemServiceImpl(ModelMapper modelMapper, OrderItemRepository repository, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderItemDto> getAllOrderItemsByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null)
            return null;

        return this.repository.findAllByOrder(order)
                .stream()
                .map(this::getMappedOrderItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto addOrderItem(OrderItemDto dto) {
        if (dto == null)
            return null;

        OrderItem orderItem = new OrderItem()
                .setProductId(dto.getProductId())
                .setQuantity(dto.getQuantity());

        OrderItem newOrderItem = this.repository.save(orderItem);
        return getMappedOrderItemDto(newOrderItem);
    }

    @Override
    public List<OrderItemDto> addOrderIntoOrderItems(Integer orderId, List<Integer> orderItemIds) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null || orderItemIds.size() <= 0)
            return null;

        List<OrderItem> orderItems = repository.findAllById(orderItemIds);
        orderItems.stream().forEach(e -> e.setOrder(order));
        List<OrderItem> newOrderItems = repository.saveAll(orderItems);
        return newOrderItems.stream().map(this::getMappedOrderItemDto).collect(Collectors.toList());
    }

    private OrderItemDto getMappedOrderItemDto(OrderItem orderItem) {
        TypeMap<OrderItem, OrderItemDto> typeMap = modelMapper.getTypeMap(OrderItem.class, OrderItemDto.class);
        OrderItemDto dto = typeMap.map(orderItem);
        if (orderItem.getOrder() != null)
            dto.setOrderId(orderItem.getOrder().getId());
        return dto;
    }
}
