package com.gca.checkoutservice.data.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderDto {
    private Integer id;
    private String sessionId;
    private String emailAddress;

    public Integer getId() {
        return id;
    }

    public OrderDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public OrderDto setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public OrderDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}
