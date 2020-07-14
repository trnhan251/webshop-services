package com.gca.shippingservice.logic.impl;

import com.gca.shippingservice.data.dto.ShippingOrderDto;
import com.gca.shippingservice.data.entities.ShippingOrder;
import com.gca.shippingservice.data.repo.ShippingOrderRepository;
import com.gca.shippingservice.logic.ShippingOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class ShippingOrderServiceImpl implements ShippingOrderService {
    private final ModelMapper modelMapper;
    private final ShippingOrderRepository repository;

    public ShippingOrderServiceImpl(ModelMapper modelMapper, ShippingOrderRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public ShippingOrderDto createShippingOrder(ShippingOrderDto dto) {
        ShippingOrder shippingOrder = new ShippingOrder()
                .setOrderId(dto.getOrderId())
                .setCity(dto.getCity())
                .setCountry(dto.getCountry())
                .setEmailAddress(dto.getEmailAddress())
                .setState(dto.getState())
                .setStreetAddress(dto.getStreetAddress())
                .setTotalCost(dto.getTotalCost())
                .setZipCode(dto.getZipCode());
        double shippingCost = dto.getTotalCost() <= 100 ? 10 : 0;
        shippingOrder.setShippingCost(shippingCost);
        shippingOrder.setTrackingNumber(generateRandomTrackingNumber());
        ShippingOrder newShippingOrder = repository.save(shippingOrder);
        return getMappedShippingOrderDto(newShippingOrder);
    }

    private String generateRandomTrackingNumber() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 25;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    private ShippingOrderDto getMappedShippingOrderDto(ShippingOrder shippingOrder) {
        TypeMap<ShippingOrder, ShippingOrderDto> typeMap = modelMapper.getTypeMap(ShippingOrder.class, ShippingOrderDto.class);
        ShippingOrderDto dto = typeMap.map(shippingOrder);
        return dto;
    }
}
