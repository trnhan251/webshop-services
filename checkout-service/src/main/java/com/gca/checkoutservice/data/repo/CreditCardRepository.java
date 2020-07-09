package com.gca.checkoutservice.data.repo;

import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    CreditCard findCreditCardByOrder(Order order);
}
