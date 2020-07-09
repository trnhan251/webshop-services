package com.gca.checkoutservice.data.repo;

import com.gca.checkoutservice.data.entities.DeliveryInformation;
import com.gca.checkoutservice.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInformationRepository extends JpaRepository<DeliveryInformation, Integer> {
    DeliveryInformation findDeliveryInformationByOrder(Order order);
}
