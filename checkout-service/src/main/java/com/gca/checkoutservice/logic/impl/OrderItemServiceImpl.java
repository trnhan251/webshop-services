package com.gca.checkoutservice.logic.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gca.checkoutservice.data.dto.OrderItemDto;
import com.gca.checkoutservice.data.dto.ProductDto;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.entities.OrderItem;
import com.gca.checkoutservice.data.repo.OrderItemRepository;
import com.gca.checkoutservice.data.repo.OrderRepository;
import com.gca.checkoutservice.logic.OrderItemService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    private final ModelMapper modelMapper;
    private final OrderItemRepository repository;
    private final OrderRepository orderRepository;
    @Value("${url.catalog}")
    private String catalogUrl;

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

    @Override
    public Double getCostSumOfOrderItems(List<Integer> orderItemIds) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonRequestBody = new Gson().toJson(orderItemIds);

        HttpEntity<String> entity = new HttpEntity<String>(jsonRequestBody, headers);

        ResponseEntity<List<ProductDto>> response = restTemplate
                .exchange(catalogUrl + "catalog/product",
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<List<ProductDto>>() {});
        List<ProductDto> productDtoList = response.getBody();
        List<OrderItem> orderItemList = repository.findAllById(orderItemIds);

        return calculateCost(productDtoList, orderItemList);
    }

    private Double calculateCost(List<ProductDto> productDtos, List<OrderItem> orderItems) {
        final double[] cost = {0};
        orderItems.stream().forEach(item -> {
            ProductDto productDto = productDtos.stream()
                    .filter(product -> product.getId() == item.getProductId())
                    .findAny().orElse(null);
            cost[0] += productDto != null ? item.getQuantity()*productDto.getPrice() : 0;
        });
        return cost[0];
    }

    private OrderItemDto getMappedOrderItemDto(OrderItem orderItem) {
        TypeMap<OrderItem, OrderItemDto> typeMap = modelMapper.getTypeMap(OrderItem.class, OrderItemDto.class);
        OrderItemDto dto = typeMap.map(orderItem);
        if (orderItem.getOrder() != null)
            dto.setOrderId(orderItem.getOrder().getId());
        return dto;
    }
}
