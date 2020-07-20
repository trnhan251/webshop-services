package com.gca.checkout.services.implementation;

import com.gca.checkout.dto.ShippingOrderDto;
import com.gca.checkout.dto.TrackingShippingOrderDto;
import com.gca.checkout.services.BasicAuthInterceptor;
import com.gca.checkout.services.ShippingService;
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
@Profile("dev")
public class DevShippingServiceImpl implements ShippingService {

    private Client client;

    public DevShippingServiceImpl(

    ) {
        Gson gson = new GsonBuilder().setDateFormat("MM/yyyy").create();
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("dev_u", "dev_p"))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9084")
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        client = retrofit.create(Client.class);
    }

    @Override
    public TrackingShippingOrderDto addShippingOrder(ShippingOrderDto shippingOrderDto) throws IOException {
        Response<TrackingShippingOrderDto> response = client.addShippingOrder(shippingOrderDto).execute();

        return response.body();
    }
}
