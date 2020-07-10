package com.gca.checkoutservice.data.dto;

import com.gca.checkoutservice.data.entities.CreditCard;
import com.gca.checkoutservice.data.entities.DeliveryInformation;
import com.gca.checkoutservice.data.entities.OrderItem;

import javax.persistence.*;
import java.util.List;

public class OrderDto {
    private Integer id;
    private String sessionId;
    private String emailAddress;
    private Double totalCost;
    private Integer deliveryInfoId;
    private Integer creditCardId;
    private List<Integer> listOrderItemIds;

    public Integer getId() {
        return id;
    }

    public OrderDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public OrderDto setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public OrderDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public OrderDto setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public Integer getDeliveryInfoId() {
        return deliveryInfoId;
    }

    public OrderDto setDeliveryInfoId(Integer deliveryInfoId) {
        this.deliveryInfoId = deliveryInfoId;
        return this;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public OrderDto setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
        return this;
    }

    public List<Integer> getListOrderItemIds() {
        return listOrderItemIds;
    }

    public OrderDto setListOrderItemIds(List<Integer> listOrderItemIds) {
        this.listOrderItemIds = listOrderItemIds;
        return this;
    }
}
