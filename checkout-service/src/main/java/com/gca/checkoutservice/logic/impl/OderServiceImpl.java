package com.gca.checkoutservice.logic.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gca.checkoutservice.data.dto.OrderDto;
import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.DeliveryInformation;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.entities.OrderItem;
import com.gca.checkoutservice.data.repo.CreditCardRepository;
import com.gca.checkoutservice.data.repo.DeliveryInformationRepository;
import com.gca.checkoutservice.data.repo.OrderItemRepository;
import com.gca.checkoutservice.data.repo.OrderRepository;
import com.gca.checkoutservice.logic.OrderItemService;
import com.gca.checkoutservice.logic.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderRepository repository;
    private final CreditCardRepository creditCardRepository;
    private final DeliveryInformationRepository deliveryInformationRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;

    public OderServiceImpl(ModelMapper modelMapper,
                           OrderRepository repository,
                           CreditCardRepository creditCardRepository,
                           DeliveryInformationRepository deliveryInformationRepository,
                           OrderItemRepository orderItemRepository, OrderItemService orderItemService) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.creditCardRepository = creditCardRepository;
        this.deliveryInformationRepository = deliveryInformationRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return this.repository.findAll().stream().map(this::getMappedOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrder(OrderDto dto) throws JsonProcessingException {
        if (dto.getCreditCardId() == null || dto.getDeliveryInfoId() == null || dto.getListOrderItemIds() == null)
            return null;

        CreditCard creditCard = creditCardRepository.findById(dto.getCreditCardId()).orElse(null);
        if (creditCard == null)
            return null;

        DeliveryInformation deliveryInformation = deliveryInformationRepository.findById(dto.getDeliveryInfoId()).orElse(null);
        if (deliveryInformation == null)
            return null;

        List<OrderItem> orderItems = orderItemRepository.findAllById(dto.getListOrderItemIds());
        if (orderItems == null)
            return null;

        Double totalCost = orderItemService
                            .getCostSumOfOrderItems(orderItems.stream().map(OrderItem::getId).collect(Collectors.toList()));

        Order order = new Order()
                .setSessionId(dto.getSessionId())
                .setEmailAddress(dto.getEmailAddress())
                .setCreditCard(creditCard)
                .setDeliveryInformation(deliveryInformation)
                .setListOrderItems(orderItems)
                .setTotalCost(totalCost);
        Order newOrder = repository.save(order);

        creditCard.setOrder(newOrder);
        creditCardRepository.save(creditCard);

        deliveryInformation.setOrder(newOrder);
        deliveryInformationRepository.save(deliveryInformation);

        orderItems.stream().forEach(e -> e.setOrder(newOrder));
        orderItemRepository.saveAll(orderItems);

        return getMappedOrderDto(newOrder);
    }

    private OrderDto getMappedOrderDto(Order order) {
        TypeMap<Order, OrderDto> typeMap = modelMapper.getTypeMap(Order.class, OrderDto.class);
        return typeMap.map(order)
                .setCreditCardId(order.getCreditCard().getId())
                .setDeliveryInfoId(order.getDeliveryInformation().getId())
                .setListOrderItemIds(order.getListOrderItems().stream().map(OrderItem::getId).collect(Collectors.toList()));
    }
}
