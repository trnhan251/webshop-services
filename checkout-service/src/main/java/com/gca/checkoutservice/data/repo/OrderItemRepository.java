package com.gca.checkoutservice.data.repo;

import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrder(Order order);
}
