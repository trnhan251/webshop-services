package com.gca.cartservice.rest;

import com.gca.cartservice.data.dto.OrderDto;
import com.gca.cartservice.logic.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final OrderService orderService;

    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    ResponseEntity<List<OrderDto>> getAllOrdersBySessionId(HttpServletRequest request) {
        return ResponseEntity.of(Optional.of(this.orderService.getAllBySessionId(request.getSession().getId())));
    }

    @PutMapping()
    ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        OrderDto dto = this.orderService.addOrder(orderDto, request.getSession().getId());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteOrder(@PathVariable Integer id, HttpServletRequest request) {
        boolean isDeleted = this.orderService.delete(id, request.getSession().getId());
        return ResponseEntity.ok(isDeleted);
    }

    @DeleteMapping()
    ResponseEntity<Boolean> deleteAll(HttpServletRequest request) {
        boolean isDeleted = this.orderService.deleteAllBySessionId(request.getSession().getId());
        return ResponseEntity.ok(isDeleted);
    }
}
