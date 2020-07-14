package com.gca.checkoutservice.data.dto;

import java.util.List;

public class CheckoutOrderDto {
    private String creditCardNumber;
    private String creditCardMonth;
    private Integer creditCardYear;
    private Integer creditCardCvv;
    private String streetAddress;
    private Integer zipCode;
    private String city;
    private String state;
    private String country;
    private String sessionId;
    private String emailAddress;
    private List<OrderItemDto> orderItemList;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public CheckoutOrderDto setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public String getCreditCardMonth() {
        return creditCardMonth;
    }

    public CheckoutOrderDto setCreditCardMonth(String creditCardMonth) {
        this.creditCardMonth = creditCardMonth;
        return this;
    }

    public Integer getCreditCardYear() {
        return creditCardYear;
    }

    public CheckoutOrderDto setCreditCardYear(Integer creditCardYear) {
        this.creditCardYear = creditCardYear;
        return this;
    }

    public Integer getCreditCardCvv() {
        return creditCardCvv;
    }

    public CheckoutOrderDto setCreditCardCvv(Integer creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public CheckoutOrderDto setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public CheckoutOrderDto setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CheckoutOrderDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public CheckoutOrderDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CheckoutOrderDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public CheckoutOrderDto setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public CheckoutOrderDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public List<OrderItemDto> getOrderItemList() {
        return orderItemList;
    }

    public CheckoutOrderDto setOrderItemList(List<OrderItemDto> orderItemList) {
        this.orderItemList = orderItemList;
        return this;
    }
}
