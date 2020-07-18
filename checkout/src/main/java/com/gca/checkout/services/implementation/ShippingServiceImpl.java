package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.ShippingOrderDto;
import com.gca.checkout.dto.TrackingShippingOrderDto;
import com.gca.checkout.services.BasicAuthInterceptor;
import com.gca.checkout.services.CartService;
import com.gca.checkout.services.ShippingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class ShippingServiceImpl implements ShippingService{

    @Value("${WEBSHOP_SHIPPING_AUTH_USERNAME}")
    private String username;

    @Value("${WEBSHOP_SHIPPING_AUTH_PASSWORD}")
    private String password;

    @Value("${WEBSHOP_SHIPPING_HOST}")
    private String host;

    @Value("${WEBSHOP_SHIPPING_PORT}")
    private String port;

    private ShippingService.Client client;

    public ShippingServiceImpl() {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username,password))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + host + ":" + port)
                .client(okHttp)
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
