package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.CartDto;
import com.gca.checkout.services.BasicAuthInterceptor;
import com.gca.checkout.services.CartService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
@Profile("production")
public class ProdCartServiceImpl implements CartService {


    private CartService.Client client;

    public ProdCartServiceImpl(
            @Value("${WEBSHOP_CART_AUTH_USERNAME}") String username,
            @Value("${WEBSHOP_CART_AUTH_PASSWORD}") String password,
            @Value("${WEBSHOP_CART_HOST}") String host,
            @Value("${WEBSHOP_CART_PORT}") String port
    ) {
        Gson gson = new GsonBuilder().setDateFormat("MM/yyyy").create();
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username, password))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + host + ":" + port)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        client = retrofit.create(CartService.Client.class);
    }


    @Override
    public CartDto getCart(Integer id) throws IOException {
        Response<CartDto> response = client.getCart(id).execute();

        return response.body();
    }
}
