package com.gca.catalogservice.common.config;

import com.gca.catalogservice.data.entities.Product;
import com.gca.catalogservice.data.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Product.class, ProductDto.class);
        return modelMapper;
    }
}
