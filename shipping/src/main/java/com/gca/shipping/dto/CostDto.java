package com.gca.shipping.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Embeddable;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Embeddable
public class CostDto {

    @PositiveOrZero
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal shipping = BigDecimal.ZERO;

    @PositiveOrZero
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal total = BigDecimal.ZERO;

    public BigDecimal getShipping() {
        return shipping;
    }

    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
