package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.*;
import com.gca.checkout.services.CartService;
import com.gca.checkout.services.CheckoutService;
import com.gca.checkout.services.CatalogService;
import com.gca.checkout.services.ShippingService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CatalogService catalogService;
    private final CartService cartService;
    private final ShippingService shippingService;

    public CheckoutServiceImpl(CatalogService catalogService, CartService cartService, ShippingService shippingService) {
        this.catalogService = catalogService;
        this.cartService = cartService;
        this.shippingService = shippingService;
    }

    @Override
    public TrackingShippingOrderDto checkout(CheckoutDto checkoutDto) throws IOException {
        CartDto cartDto = cartService.getCart(checkoutDto.getCartId());

        List<ProductDto> productDtos = catalogService.getProducts(cartDto.getProductIds());

        ShippingOrderDto shippingOrderDto = new ShippingOrderDto();
        shippingOrderDto.setProducts(productDtos);
        shippingOrderDto.setAddress(checkoutDto.getAddress());
        shippingOrderDto.setEmail(checkoutDto.getEmail());
        shippingOrderDto.setCreditCard(checkoutDto.getCreditCard());

        return shippingService.addShippingOrder(shippingOrderDto);
    }
}
