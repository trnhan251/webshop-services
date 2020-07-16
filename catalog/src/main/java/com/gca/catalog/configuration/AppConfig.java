package com.gca.catalog.configuration;

import com.gca.catalog.dto.ProductDto;
import com.gca.catalog.entities.Product;
import io.github.resilience4j.retry.RetryRegistry;
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
