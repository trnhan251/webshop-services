package com.gca.cart.configuration;

import com.gca.cart.entities.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Cart.class, Cart.class);
        return modelMapper;
    }

}
