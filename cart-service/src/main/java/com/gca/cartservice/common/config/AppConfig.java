package com.gca.cartservice.common.config;

import com.gca.cartservice.data.dto.OrderDto;
import com.gca.cartservice.data.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Order.class, OrderDto.class);
        return modelMapper;
    }
}
