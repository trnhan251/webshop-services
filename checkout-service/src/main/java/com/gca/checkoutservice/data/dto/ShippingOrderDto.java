package com.gca.checkoutservice.data.dto;

public class ShippingOrderDto {
    private Integer id;
    private Integer orderId;
    private String streetAddress;
    private Integer zipCode;
    private String city;
    private String state;
    private String country;
    private String emailAddress;
    private Double totalCost;
    private String trackingNumber;
    private Double shippingCost;

    public Integer getId() {
        return id;
    }

    public ShippingOrderDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public ShippingOrderDto setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public ShippingOrderDto setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public ShippingOrderDto setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingOrderDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public ShippingOrderDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ShippingOrderDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ShippingOrderDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public ShippingOrderDto setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public ShippingOrderDto setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public ShippingOrderDto setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }
}
