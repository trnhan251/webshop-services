package com.gca.shipping.services.implementation;

import com.gca.shipping.dto.CostDto;
import com.gca.shipping.dto.ProductDto;
import com.gca.shipping.dto.ShippingOrderDto;
import com.gca.shipping.dto.TrackingShippingOrderDto;
import com.gca.shipping.entities.ShippingOrder;
import com.gca.shipping.repositories.ShippingRepository;
import com.gca.shipping.services.CostService;
import com.gca.shipping.services.ShippingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShippingServiceImpl implements ShippingService {

    private CostService costService;
    private ShippingRepository shippingRepository;
    private ModelMapper modelMapper;

    public ShippingServiceImpl(CostService costService, ShippingRepository shippingRepository, ModelMapper modelMapper) {
        this.costService = costService;
        this.shippingRepository = shippingRepository;
        this.modelMapper = modelMapper;
    }


    public TrackingShippingOrderDto addShippingOrder(ShippingOrderDto shippingOrderDto) {
        String id = UUID.randomUUID().toString();

        List<BigDecimal> prices = shippingOrderDto.getProducts().stream()
                .map(ProductDto::getPrice)
                .collect(Collectors.toList());

        CostDto costDto = costService.calc(prices);

        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setAddress(shippingOrderDto.getAddress());
        shippingOrder.setCreditCard(shippingOrderDto.getCreditCard());
        shippingOrder.setEmail(shippingOrderDto.getEmail());
        shippingOrder.setProducts(shippingOrderDto.getProducts());
        shippingOrder.setCost(costDto);
        shippingOrder.setTrackingNumber(id);

        shippingRepository.save(shippingOrder);
        System.out.println("HERE I AM");
        return modelMapper.map(shippingOrder, TrackingShippingOrderDto.class);
    }

    public Optional<TrackingShippingOrderDto> getShippingOrder(String id) {
        Optional<ShippingOrder> shippingOrder = shippingRepository.findById(id);
        if (!shippingOrder.isPresent()) return Optional.empty();

        return Optional.of(modelMapper.map(shippingOrder.get(), TrackingShippingOrderDto.class));
    }

}
