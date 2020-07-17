package com.gca.shipping.entities;

import com.gca.shipping.dto.AddressDto;
import com.gca.shipping.dto.CostDto;
import com.gca.shipping.dto.CreditCardDto;
import com.gca.shipping.dto.ProductDto;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class ShippingOrder {

    @Id
    private String trackingNumber;

    @Embedded
    private CostDto cost;

    @Embedded
    private AddressDto address;

    private String email;

    @Embedded
    private CreditCardDto creditCard;

    @ElementCollection
    private List<ProductDto> products;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public CreditCardDto getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDto creditCard) {
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public CostDto getCost() {
        return cost;
    }

    public void setCost(CostDto cost) {
        this.cost = cost;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
