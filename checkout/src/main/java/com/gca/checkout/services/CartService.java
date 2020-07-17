package com.gca.checkout.services;

import com.gca.checkout.dto.CartDto;
import retrofit2.Call;
import retrofit2.http.GET;

import java.io.IOException;

public interface CartService {
    CartDto getCart(Integer id) throws IOException;

    public interface Client{
        @GET("/{id}")
        Call<CartDto> getCart(Integer id);
    }
}