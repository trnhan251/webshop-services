package com.gca.checkout.services;

import com.gca.checkout.dto.TrackingShippingOrderDto;
import com.gca.checkout.dto.UserInfoDto;

import java.io.IOException;

public interface CheckoutService {
    TrackingShippingOrderDto checkout(UserInfoDto userInfoDto) throws IOException;
}
