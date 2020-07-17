package com.gca.shipping.configuration;

import com.gca.shipping.dto.TrackingShippingOrderDto;
import com.gca.shipping.entities.ShippingOrder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(ShippingOrder.class, TrackingShippingOrderDto.class);
        return modelMapper;
    }

}
