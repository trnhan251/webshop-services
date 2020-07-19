package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.ProductDto;
import com.gca.checkout.services.BasicAuthInterceptor;
import com.gca.checkout.services.CartService;
import com.gca.checkout.services.CatalogService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogService.Client client;

    public CatalogServiceImpl(
            @Value("${WEBSHOP_CATALOG_AUTH_USERNAME}")     String username,
            @Value("${WEBSHOP_CATALOG_AUTH_PASSWORD}")     String password,
            @Value("${WEBSHOP_CATALOG_HOST}")     String host,
            @Value("${WEBSHOP_CATALOG_PORT}")     String port
    ) {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(username,password))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + host + ":" + port)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(CatalogService.Client.class);
    }

    @Override
    public List<ProductDto> getProducts(List<Integer> ids) throws IOException {
        Response<List<ProductDto>> response = client.getProducts(ids).execute();

        return response.body();
    }
}
