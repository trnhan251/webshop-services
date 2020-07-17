package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.ProductDto;
import com.gca.checkout.services.ProductService;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductService.Client client;

    public ProductServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(ProductService.Client.class);
    }

    @Override
    public List<ProductDto> getProducts(List<Integer> ids) throws IOException {
        Response<List<ProductDto>> response = client.getProducts(ids).execute();

        return response.body();
    }
}
