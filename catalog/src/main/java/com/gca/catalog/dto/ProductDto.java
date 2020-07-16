package com.gca.catalog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@ApiModel(value = "Product",description = "A product")
public class ProductDto {

    @ApiModelProperty(value = "The unique identifier of the given product", required = true)
    private Integer id;

    @ApiModelProperty(value = "The name of the given product", required = true)
    private String name;

    @ApiModelProperty(value = "The price in euro of the given product", required = true)
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
