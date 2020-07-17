package com.gca.cart.services;

import com.gca.cart.dto.CartDto;

import java.util.Optional;
import java.util.Set;

public interface CartService {
    CartDto getCart(Integer cartId);

    CartDto putCart(Integer cartId, Set<Integer> productIds);

    CartDto emptyCart(Integer cartId);
}
