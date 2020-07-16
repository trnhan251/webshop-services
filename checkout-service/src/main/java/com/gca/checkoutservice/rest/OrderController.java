package com.gca.checkoutservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gca.checkoutservice.data.dto.*;
import com.gca.checkoutservice.logic.CreditCardService;
import com.gca.checkoutservice.logic.DeliveryInformationService;
import com.gca.checkoutservice.logic.OrderItemService;
import com.gca.checkoutservice.logic.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    public OrderController(CreditCardService creditCardService,
                           DeliveryInformationService deliveryInformationService,
                           OrderItemService orderItemService,
                           OrderService orderService) {
        this.creditCardService = creditCardService;
        this.deliveryInformationService = deliveryInformationService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<ShippingOrderDto> checkout(@RequestBody CheckoutOrderDto dto) throws Exception {
        LOGGER.info("POST - Checkout with CheckoutOrderDto");
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
        ShippingOrderDto shippingOrderDto = orderService.createOrder(orderDto);

        return shippingOrderDto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(shippingOrderDto);
    }

    @PostMapping("/credit-card")
    public ResponseEntity<CreditCardDto> addCreditCard(@RequestBody CreditCardDto creditCardDto) throws Exception {
        LOGGER.info("POST - Add new Credit card");
        CreditCardDto dto = creditCardService.createCreditCard(creditCardDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/delivery-information")
    public ResponseEntity<DeliveryInformationDto> addDeliveryInformation(@RequestBody DeliveryInformationDto deliveryInformationDto) throws Exception {
        LOGGER.info("POST - Add new Delivery Information");
        DeliveryInformationDto dto = deliveryInformationService.addDeliveryInformation(deliveryInformationDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/order-item")
    public ResponseEntity<OrderItemDto> addOrderItem(@RequestBody OrderItemDto orderItemDto) {
        LOGGER.info("POST - Add new Order Item");
        OrderItemDto dto = orderItemService.addOrderItem(orderItemDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/order")
    public ResponseEntity<ShippingOrderDto> addOrder(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        LOGGER.info("POST - Add new Order");
        ShippingOrderDto dto = orderService.createOrder(orderDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }

    @PostMapping("/cost-sum-calculation")
    public ResponseEntity<Double> getCostSum(@RequestBody List<Integer> orderItemIds) throws JsonProcessingException {
        LOGGER.info("POST - Get Cost Sum");
        Double costSum = orderItemService.getCostSumOfOrderItems(orderItemIds);
        return costSum == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(costSum);
    }
}
