package com.gca.checkoutservice.data.dto;

import javax.persistence.Column;

public class CreditCardDto {
    private Integer id;
    private String creditCardNumber;
    private String creditCardMonth;
    private Integer creditCardYear;
    private Integer creditCardCvv;
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public CreditCardDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public CreditCardDto setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public String getCreditCardMonth() {
        return creditCardMonth;
    }

    public CreditCardDto setCreditCardMonth(String creditCardMonth) {
        this.creditCardMonth = creditCardMonth;
        return this;
    }

    public Integer getCreditCardYear() {
        return creditCardYear;
    }

    public CreditCardDto setCreditCardYear(Integer creditCardYear) {
        this.creditCardYear = creditCardYear;
        return this;
    }

    public Integer getCreditCardCvv() {
        return creditCardCvv;
    }

    public CreditCardDto setCreditCardCvv(Integer creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public CreditCardDto setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }
}
