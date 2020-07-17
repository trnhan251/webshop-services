package com.gca.checkout.services;

import com.gca.checkout.dto.ShippingOrderDto;
import com.gca.checkout.dto.TrackingShippingOrderDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.Optional;

public interface ShippingService {

    TrackingShippingOrderDto addShippingOrder(ShippingOrderDto shippingOrderDto) throws IOException;

    public interface Client {
        @POST("")
        Call<TrackingShippingOrderDto> addShippingOrder(ShippingOrderDto shippingOrderDto);
    }
}
