package com.gca.cart.dto;

import io.micrometer.core.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

public class CartDto {

    @NonNull
    private Integer id;

    @NonNull
    private Set<Integer> productIds = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Integer> productIds) {
        this.productIds = productIds;
    }
}
