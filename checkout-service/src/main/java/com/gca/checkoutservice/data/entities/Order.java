package com.gca.checkoutservice.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "email_address")
    private String emailAddress;
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryInformation deliveryInformation;
    @OneToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> listOrderItems;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Order setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Order setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public DeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }

    public Order setDeliveryInformation(DeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
        return this;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Order setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public List<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public Order setListOrderItems(List<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
        return this;
    }
}
