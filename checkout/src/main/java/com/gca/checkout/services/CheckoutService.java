package com.gca.checkout.services;

import com.gca.checkout.dto.TrackingShippingOrderDto;
import com.gca.checkout.dto.CheckoutDto;

import java.io.IOException;

public interface CheckoutService {
    TrackingShippingOrderDto checkout(CheckoutDto checkoutDto) throws IOException;
}
