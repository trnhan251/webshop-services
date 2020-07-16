package com.gca.catalog.dto;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class ProductDto {

    private Integer id;

    private String name;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
