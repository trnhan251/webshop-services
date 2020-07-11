package com.gca.checkoutservice.logic.impl;

import com.gca.checkoutservice.data.dto.DeliveryInformationDto;
import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.DeliveryInformation;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.repo.DeliveryInformationRepository;
import com.gca.checkoutservice.data.repo.OrderRepository;
import com.gca.checkoutservice.logic.DeliveryInformationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeliveryInformationServiceImpl implements DeliveryInformationService {
    private final ModelMapper modelMapper;
    private final DeliveryInformationRepository repository;
    private final OrderRepository orderRepository;

    public DeliveryInformationServiceImpl(ModelMapper modelMapper, DeliveryInformationRepository repository,
                                          OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<DeliveryInformationDto> getAllDeliveryInformation() {
        return this.repository.findAll()
                .stream()
                .map(this::getMappedDeliveryInformationDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryInformationDto addDeliveryInformation(DeliveryInformationDto dto) throws Exception {
        if (dto == null)
            return null;

        DeliveryInformation deliveryInformation = new DeliveryInformation()
                .setCountry(dto.getCountry())
                .setCity(dto.getCity())
                .setState(dto.getState())
                .setStreetAddress(dto.getStreetAddress())
                .setZipCode(dto.getZipCode());

        DeliveryInformation newDeliveryInformation = repository.save(deliveryInformation);
        return getMappedDeliveryInformationDto(newDeliveryInformation);
    }

    @Override
    public DeliveryInformationDto getDeliveryInformationByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null)
            return null;
        DeliveryInformation deliveryInformation = repository.findDeliveryInformationByOrder(order);
        return getMappedDeliveryInformationDto(deliveryInformation);
    }

    private DeliveryInformationDto getMappedDeliveryInformationDto(DeliveryInformation deliveryInformation) {
        TypeMap<DeliveryInformation, DeliveryInformationDto> typeMap = modelMapper.getTypeMap(DeliveryInformation.class,
                DeliveryInformationDto.class);
        DeliveryInformationDto dto = typeMap.map(deliveryInformation);
        if (deliveryInformation.getOrder() != null)
            dto.setOrderId(deliveryInformation.getOrder().getId());
        return dto;
    }

}
