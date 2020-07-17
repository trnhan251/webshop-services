package com.gca.shipping.repositories;

import com.gca.shipping.entities.ShippingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingOrder, String> {
}
