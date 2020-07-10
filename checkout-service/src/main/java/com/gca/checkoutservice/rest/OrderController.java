package com.gca.checkoutservice.rest;

import com.gca.checkoutservice.data.dto.CreditCardDto;
import com.gca.checkoutservice.data.dto.DeliveryInformationDto;
import com.gca.checkoutservice.data.dto.OrderDto;
import com.gca.checkoutservice.data.dto.OrderItemDto;
import com.gca.checkoutservice.logic.CreditCardService;
import com.gca.checkoutservice.logic.DeliveryInformationService;
import com.gca.checkoutservice.logic.OrderItemService;
import com.gca.checkoutservice.logic.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping()
    public ResponseEntity<OrderDto> addOrderItem(@RequestBody OrderDto orderDto) {
        OrderDto dto = orderService.createOrder(orderDto);
        return dto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(dto);
    }
}
