package com.gca.checkoutservice.common.config;

import com.gca.checkoutservice.data.dto.CreditCardDto;
import com.gca.checkoutservice.data.dto.DeliveryInformationDto;
import com.gca.checkoutservice.data.dto.OrderDto;
import com.gca.checkoutservice.data.dto.OrderItemDto;
import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.DeliveryInformation;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.entities.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CreditCard.class, CreditCardDto.class);
        modelMapper.createTypeMap(DeliveryInformation.class, DeliveryInformationDto.class);
        modelMapper.createTypeMap(OrderItem.class, OrderItemDto.class);
        modelMapper.createTypeMap(Order.class, OrderDto.class);
        return modelMapper;
    }
}
