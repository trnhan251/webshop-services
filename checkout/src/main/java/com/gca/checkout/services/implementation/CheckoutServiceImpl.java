package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.*;
import com.gca.checkout.services.CartService;
import com.gca.checkout.services.CheckoutService;
import com.gca.checkout.services.ProductService;
import com.gca.checkout.services.ShippingService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final ProductService productService;
    private final CartService cartService;
    private final ShippingService shippingService;

    public CheckoutServiceImpl(ProductService productService, CartService cartService, ShippingService shippingService) {
        this.productService = productService;
        this.cartService = cartService;
        this.shippingService = shippingService;
    }

    @Override
    public TrackingShippingOrderDto checkout(UserInfoDto userInfoDto) throws IOException {
        CartDto cartDto = cartService.getCart(userInfoDto.getCartId());

        List<ProductDto> productDtos = productService.getProducts(cartDto.getProductIds());

        ShippingOrderDto shippingOrderDto = new ShippingOrderDto();
        shippingOrderDto.setProducts(productDtos);
        shippingOrderDto.setAddress(userInfoDto.getAddress());
        shippingOrderDto.setEmail(userInfoDto.getEmail());
        shippingOrderDto.setCreditCard(userInfoDto.getCreditCard());

        return shippingService.addShippingOrder(shippingOrderDto);
    }
}
