package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.CartDto;
import com.gca.checkout.services.CartService;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class CartServiceImpl implements CartService {
    CartService.Client client;

    public CartServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9082")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(CartService.Client.class);
    }


    @Override
    public CartDto getCart(Integer id) throws IOException {
        Response<CartDto> response = client.getCart(id).execute();

        return response.body();
    }
}
