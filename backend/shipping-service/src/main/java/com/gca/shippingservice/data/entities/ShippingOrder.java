package com.gca.shippingservice.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "shipping_order")
public class ShippingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "total_cost")
    private Double totalCost;
    @Column(name = "tracking_number")
    private String trackingNumber;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "zip_code")
    private Integer zipCode;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "shipping_cost")
    private Double shippingCost;

    public Integer getId() {
        return id;
    }

    public ShippingOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public ShippingOrder setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ShippingOrder setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public ShippingOrder setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public ShippingOrder setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public ShippingOrder setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public ShippingOrder setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingOrder setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public ShippingOrder setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ShippingOrder setCountry(String country) {
        this.country = country;
        return this;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public ShippingOrder setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }
}
