package com.gca.checkoutservice.data.repo;

import com.gca.checkoutservice.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
