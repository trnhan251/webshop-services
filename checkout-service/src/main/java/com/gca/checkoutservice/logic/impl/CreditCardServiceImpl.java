package com.gca.checkoutservice.logic.impl;

import com.gca.checkoutservice.data.dto.CreditCardDto;
import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.Order;
import com.gca.checkoutservice.data.repo.CreditCardRepository;
import com.gca.checkoutservice.data.repo.OrderRepository;
import com.gca.checkoutservice.logic.CreditCardService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    private final ModelMapper modelMapper;
    private final CreditCardRepository repository;
    private final OrderRepository orderRepository;

    public CreditCardServiceImpl(ModelMapper modelMapper, CreditCardRepository repository, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CreditCardDto> getAllCreditCards() {
        return this.repository.findAll().stream().map(this::getMappedCreditCardDto).collect(Collectors.toList());
    }

    @Override
    public CreditCardDto createCreditCard(CreditCardDto dto) throws Exception {
        if (dto == null)
            return null;

        CreditCard creditCard = new CreditCard()
                .setCreditCardNumber(dto.getCreditCardNumber())
                .setCreditCardMonth(dto.getCreditCardMonth())
                .setCreditCardYear(dto.getCreditCardYear())
                .setCreditCardCvv(dto.getCreditCardCvv());

        CreditCard newCreditCard = repository.save(creditCard);

        return getMappedCreditCardDto(newCreditCard);
    }

    @Override
    public CreditCardDto getCreditCardByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null)
            return null;
        CreditCard creditCard = repository.findCreditCardByOrder(order);
        return getMappedCreditCardDto(creditCard);
    }

    private CreditCardDto getMappedCreditCardDto(CreditCard creditCard) {
        TypeMap<CreditCard, CreditCardDto> typeMap = modelMapper.getTypeMap(CreditCard.class, CreditCardDto.class);
        CreditCardDto dto = typeMap.map(creditCard);
        if (creditCard.getOrder() != null)
            dto.setOrderId(creditCard.getOrder().getId());
        return dto;
    }
}
