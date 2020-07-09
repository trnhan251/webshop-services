package com.gca.checkoutservice.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "delivery_information")
public class DeliveryInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Integer getId() {
        return id;
    }

    public DeliveryInformation setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public DeliveryInformation setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public DeliveryInformation setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public DeliveryInformation setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public DeliveryInformation setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public DeliveryInformation setCountry(String country) {
        this.country = country;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public DeliveryInformation setOrder(Order order) {
        this.order = order;
        return this;
    }
}
