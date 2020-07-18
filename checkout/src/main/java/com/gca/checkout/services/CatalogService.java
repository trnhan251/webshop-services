package com.gca.checkout.services;

import com.gca.checkout.dto.ProductDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

public interface CatalogService {

    List<ProductDto> getProducts(List<Integer> ids) throws IOException;

    public interface Client {
        @POST("/collect")
        Call<List<ProductDto>> getProducts(@Body List<Integer> ids);
    }
}
