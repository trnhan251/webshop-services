package com.gca.cartservice.rest;

import com.gca.cartservice.data.dto.OrderDto;
import com.gca.cartservice.logic.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin
    @GetMapping()
    ResponseEntity<List<OrderDto>> getAllOrdersBySessionId(@RequestParam String sessionId) {
        LOGGER.info("GET - All Orders By Session ID - " + sessionId);
        return ResponseEntity.of(Optional.of(this.orderService.getAllBySessionId(sessionId)));
    }

    @CrossOrigin
    @GetMapping("/session")
    ResponseEntity<String> registerSession(HttpServletRequest request) {
        LOGGER.info("GET - Register new Session - " + request.getSession().getId());
        return ResponseEntity.ok(request.getSession().getId());
    }

    @CrossOrigin
    @PostMapping()
    ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto, @RequestParam String sessionId) {
        LOGGER.info("POST - Add new Order for SessionID - " + sessionId);
        OrderDto dto = this.orderService.addOrder(orderDto, sessionId);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteOrder(@PathVariable Integer id, @RequestParam String sessionId) {
        LOGGER.info("DELETE - Delete Order for SessionID - " + sessionId);
        boolean isDeleted = this.orderService.delete(id, sessionId);
        return ResponseEntity.ok(isDeleted);
    }

    @CrossOrigin
    @DeleteMapping()
    ResponseEntity<Boolean> deleteAll(@RequestParam String sessionId) {
        LOGGER.info("DELETE - Delete all Orders of SessionID - " + sessionId);
        boolean isDeleted = this.orderService.deleteAllBySessionId(sessionId);
        return ResponseEntity.ok(isDeleted);
    }
}
