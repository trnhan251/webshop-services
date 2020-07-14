package com.gca.shippingservice.common.config;

import com.gca.shippingservice.data.dto.ShippingOrderDto;
import com.gca.shippingservice.data.entities.ShippingOrder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(ShippingOrder.class, ShippingOrderDto.class);
        return modelMapper;
    }
}
