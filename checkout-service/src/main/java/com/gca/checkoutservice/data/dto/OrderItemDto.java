package com.gca.checkoutservice.data.dto;

import com.gca.checkoutservice.data.entities.Order;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class OrderItemDto {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public OrderItemDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public OrderItemDto setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItemDto setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }
}
