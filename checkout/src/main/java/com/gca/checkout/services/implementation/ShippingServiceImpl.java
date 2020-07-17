package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.ShippingOrderDto;
import com.gca.checkout.dto.TrackingShippingOrderDto;
import com.gca.checkout.services.ShippingService;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class ShippingServiceImpl implements ShippingService{

    ShippingService.Client client;

    public ShippingServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9084")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(ShippingService.Client.class);
    }

    @Override
    public TrackingShippingOrderDto addShippingOrder(ShippingOrderDto shippingOrderDto) throws IOException {
        Response<TrackingShippingOrderDto> response = client.addShippingOrder(shippingOrderDto).execute();

        return response.body();
    }
}
