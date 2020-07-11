package com.gca.shippingservice.rest;

import com.gca.shippingservice.data.dto.ShippingOrderDto;
import com.gca.shippingservice.logic.ShippingOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/shipping")
public class ShippingOrderController {
    private final ShippingOrderService shippingOrderService;
    public ShippingOrderController(ShippingOrderService shippingOrderService) {
        this.shippingOrderService = shippingOrderService;
    }

    @PostMapping("/order")
    public ResponseEntity<ShippingOrderDto> createShippingOrder(@RequestBody ShippingOrderDto dto) {
        ShippingOrderDto shippingOrderDto = shippingOrderService.createShippingOrder(dto);
        return shippingOrderDto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(shippingOrderDto);
    }
}
