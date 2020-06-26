package com.gca.cartservice.data.repo;

import com.gca.cartservice.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
