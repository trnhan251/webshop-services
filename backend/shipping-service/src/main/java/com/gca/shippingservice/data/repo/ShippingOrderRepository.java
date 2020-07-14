package com.gca.shippingservice.data.repo;

import com.gca.shippingservice.data.entities.ShippingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, Integer> {
}
