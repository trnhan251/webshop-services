package com.gca.shipping.dto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;


public class ShippingOrderDto {

    @NotNull(message = "[body(address)] cannot be null")
    private AddressDto address;
    @Email(message = "[body(email)] not a valid email")
    @NotNull(message = "[body(email)] cannot be null")
    private String email;
    @NotNull(message = "[body(creditCard)] cannot be null")
    private CreditCardDto creditCard;
    @NotNull(message = "[body(products)] cannot be null")
    private List<ProductDto> products;

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCardDto getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDto creditCard) {
        this.creditCard = creditCard;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
