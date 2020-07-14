package com.gca.checkoutservice.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @Column(name = "credit_card_month")
    private String creditCardMonth;
    @Column(name = "credit_card_year")
    private Integer creditCardYear;
    @Column(name = "credit_card_cvv")
    private Integer creditCardCvv;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Integer getId() {
        return id;
    }

    public CreditCard setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public CreditCard setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public String getCreditCardMonth() {
        return creditCardMonth;
    }

    public CreditCard setCreditCardMonth(String creditCardMonth) {
        this.creditCardMonth = creditCardMonth;
        return this;
    }

    public int getCreditCardYear() {
        return creditCardYear;
    }

    public CreditCard setCreditCardYear(int creditCardYear) {
        this.creditCardYear = creditCardYear;
        return this;
    }

    public int getCreditCardCvv() {
        return creditCardCvv;
    }

    public CreditCard setCreditCardCvv(int creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public CreditCard setOrder(Order order) {
        this.order = order;
        return this;
    }
}
