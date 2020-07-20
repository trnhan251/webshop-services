package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.ProductDto;
import com.gca.checkout.services.BasicAuthInterceptor;
import com.gca.checkout.services.CatalogService;
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
import java.util.List;

@Service
@Profile("dev")
public class DevCatalogServiceImpl implements CatalogService {

    private Client client;

    public DevCatalogServiceImpl(

    ) {

        Gson gson = new GsonBuilder().setDateFormat("MM/yyyy").create();
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("dev_u", "dev_p"))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9081")
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        client = retrofit.create(Client.class);
    }

    @Override
    public List<ProductDto> getProducts(List<Integer> ids) throws IOException {
        Response<List<ProductDto>> response = client.getProducts(ids).execute();

        return response.body();
    }
}
