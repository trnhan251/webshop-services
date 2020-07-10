package com.gca.checkoutservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gca.checkoutservice.data.dto.*;
import com.gca.checkoutservice.logic.CreditCardService;
import com.gca.checkoutservice.logic.DeliveryInformationService;
import com.gca.checkoutservice.logic.OrderItemService;
import com.gca.checkoutservice.logic.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final CreditCardService creditCardService;
    private final DeliveryInformationService deliveryInformationService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    public OrderController(CreditCardService creditCardService,
                           DeliveryInformationService deliveryInformationService,
                           OrderItemService orderItemService,
                           OrderService orderService) {
        this.creditCardService = creditCardService;
        this.deliveryInformationService = deliveryInformationService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<OrderDto> checkout(@RequestBody CheckoutOrderDto dto) throws Exception {
        CreditCardDto creditCardDto = new CreditCardDto()
                .setCreditCardCvv(dto.getCreditCardCvv())
                .setCreditCardMonth(dto.getCreditCardMonth())
                .setCreditCardYear(dto.getCreditCardYear())
                .setCreditCardNumber(dto.getCreditCardNumber());
        creditCardDto = creditCardService.createCreditCard(creditCardDto);

        DeliveryInformationDto deliveryDto = new DeliveryInformationDto()
                .setCity(dto.getCity())
                .setCountry(dto.getCountry())
                .setState(dto.getState())
                .setStreetAddress(dto.getStreetAddress())
                .setZipCode(dto.getZipCode());
        deliveryDto = deliveryInformationService.addDeliveryInformation(deliveryDto);

        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        dto.getOrderItemList().stream().forEach(item -> {
            OrderItemDto orderItemDto = orderItemService.addOrderItem(item);
            if (orderItemDto != null) orderItemDtoList.add(orderItemDto);
        });

        OrderDto orderDto = new OrderDto()
                .setEmailAddress(dto.getEmailAddress())
                .setSessionId(dto.getSessionId())
                .setCreditCardId(creditCardDto.getId())
                .setDeliveryInfoId(deliveryDto.getId())
                .setListOrderItemIds(orderItemDtoList.stream().map(OrderItemDto::getId).collect(Collectors.toList()));
        orderDto = orderService.createOrder(orderDto);

        return orderDto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(orderDto);
    }

    @PostMapping("/credit-card")
    public ResponseEntity<CreditCardDto> addCreditCard(@RequestBody CreditCardDto creditCardDto) throws Exception {
        CreditCardDto dto = creditCardService.createCreditCard(creditCardDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/delivery-information")
    public ResponseEntity<DeliveryInformationDto> addDeliveryInformation(@RequestBody DeliveryInformationDto deliveryInformationDto) throws Exception {
        DeliveryInformationDto dto = deliveryInformationService.addDeliveryInformation(deliveryInformationDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/order-item")
    public ResponseEntity<OrderItemDto> addOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItemDto dto = orderItemService.addOrderItem(orderItemDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDto> addOrderItem(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        OrderDto dto = orderService.createOrder(orderDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/cost-sum-calculation")
    public ResponseEntity<Double> getCostSum(@RequestBody List<Integer> orderItemIds) throws JsonProcessingException {
        Double costSum = orderItemService.getCostSumOfOrderItems(orderItemIds);
        return costSum == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(costSum);
    }
}
