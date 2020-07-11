package com.gca.shippingservice.logic;

import com.gca.shippingservice.data.dto.ShippingOrderDto;

public interface ShippingOrderService {
    ShippingOrderDto createShippingOrder(ShippingOrderDto dto);
}
