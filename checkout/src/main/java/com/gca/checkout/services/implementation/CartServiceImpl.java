package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.CartDto;
import com.gca.checkout.services.BasicAuthInterceptor;
import com.gca.checkout.services.CartService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class CartServiceImpl implements CartService {

    @Value("${WEBSHOP_CART_AUTH_USERNAME}")
    private String username;

    @Value("${WEBSHOP_CART_AUTH_PASSWORD}")
    private String password;

    @Value("${WEBSHOP_CART_HOST}")
    private String host;

    @Value("${WEBSHOP_CART_PORT}")
    private String port;

    private CartService.Client client;

    public CartServiceImpl() {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username,password))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + host + ":" + port)
                .client(okHttp)
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
