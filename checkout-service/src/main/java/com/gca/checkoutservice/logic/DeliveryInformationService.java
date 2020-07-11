package com.gca.checkoutservice.logic;

import com.gca.checkoutservice.data.dto.DeliveryInformationDto;

import java.util.List;

public interface DeliveryInformationService {
    List<DeliveryInformationDto> getAllDeliveryInformation();
    DeliveryInformationDto addDeliveryInformation(DeliveryInformationDto dto) throws Exception;
    DeliveryInformationDto getDeliveryInformationByOrderId(Integer orderId);
}
