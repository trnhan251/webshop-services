package com.gca.shipping.services;

import com.gca.shipping.dto.ShippingOrderDto;
import com.gca.shipping.dto.TrackingShippingOrderDto;

import java.util.Optional;

public interface ShippingService {
    Optional<TrackingShippingOrderDto> getShippingOrder(String id);
    TrackingShippingOrderDto addShippingOrder(ShippingOrderDto shippingOrderDto);
}
