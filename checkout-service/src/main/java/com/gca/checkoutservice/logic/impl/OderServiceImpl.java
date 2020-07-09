package com.gca.checkoutservice.logic.impl;

import com.gca.checkoutservice.data.dto.CreditCardDto;
import com.gca.checkoutservice.data.dto.DeliveryInformationDto;
import com.gca.checkoutservice.data.dto.OrderDto;
import com.gca.checkoutservice.data.dto.OrderItemDto;
import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.DeliveryInformation;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.entities.OrderItem;
import com.gca.checkoutservice.data.repo.CreditCardRepository;
import com.gca.checkoutservice.data.repo.DeliveryInformationRepository;
import com.gca.checkoutservice.data.repo.OrderItemRepository;
import com.gca.checkoutservice.data.repo.OrderRepository;
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

    public OderServiceImpl(ModelMapper modelMapper,
                           OrderRepository repository,
                           CreditCardRepository creditCardRepository,
                           DeliveryInformationRepository deliveryInformationRepository,
                           OrderItemRepository orderItemRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.creditCardRepository = creditCardRepository;
        this.deliveryInformationRepository = deliveryInformationRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return this.repository.findAll().stream().map(this::getMappedOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrder(OrderDto dto,
                                CreditCardDto creditCardDto,
                                DeliveryInformationDto deliveryInformationDto,
                                List<OrderItemDto> orderItemDtos) {
        if (creditCardDto == null || deliveryInformationDto == null || orderItemDtos == null)
            return null;

        CreditCard creditCard = creditCardRepository.findById(creditCardDto.getId()).orElse(null);
        if (creditCard == null)
            return null;

        DeliveryInformation deliveryInformation = deliveryInformationRepository.findById(deliveryInformationDto.getId()).orElse(null);
        if (deliveryInformation == null)
            return null;

        List<OrderItem> orderItems = orderItemRepository.findAllById(orderItemDtos.stream()
                                                                        .map(OrderItemDto::getId)
                                                                        .collect(Collectors.toList()));
        if (orderItems == null)
            return null;

        Order order = new Order()
                .setSessionId(dto.getSessionId())
                .setEmailAddress(dto.getEmailAddress())
                .setCreditCard(creditCard)
                .setDeliveryInformation(deliveryInformation)
                .setListOrderItems(orderItems);
        Order newOrder = repository.save(order);
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
