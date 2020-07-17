package com.gca.cart.services.implementation;

import com.gca.cart.dto.CartDto;
import com.gca.cart.entities.Cart;
import com.gca.cart.repositories.CartRepository;
import com.gca.cart.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    public CartDto getCart(Integer cartId) {
        return modelMapper.map(getCartOrCreate(cartId), CartDto.class);
    }

    public CartDto putCart(Integer cartId, Set<Integer> productIds) {
        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setProductIds(productIds);

        return modelMapper.map(cartRepository.save(cart), CartDto.class);
    }

    public CartDto emptyCart(Integer cartId) {
        Cart cart = getCartOrCreate(cartId);

        cart.setProductIds(new HashSet<>());
        cartRepository.save(cart);

        return modelMapper.map(cart, CartDto.class);
    }

    private Cart getCartOrCreate(Integer cartId) {
        return cartRepository.findById(cartId).orElse(cartRepository.saveAndFlush(new Cart()));
    }

}
