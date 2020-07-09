package com.gca.checkoutservice.data.dto;

import javax.persistence.Column;

public class DeliveryInformationDto {
    private Integer id;
    private String streetAddress;
    private Integer zipCode;
    private String city;
    private String state;
    private String country;

    public Integer getId() {
        return id;
    }

    public DeliveryInformationDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public DeliveryInformationDto setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public DeliveryInformationDto setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public DeliveryInformationDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public DeliveryInformationDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public DeliveryInformationDto setCountry(String country) {
        this.country = country;
        return this;
    }
}
