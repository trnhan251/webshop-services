package com.gca.shipping.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TrackingShippingOrderDto extends ShippingOrderDto {

    @NotNull(message = "[body(trackingNumber)] trackingNumber cannot be null")
    private String trackingNumber;

    @NotNull(message = "[body(cost)] trackingNumber cannot be null")
    private CostDto cost;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public CostDto getCost() {
        return cost;
    }

    public void setCost(CostDto cost) {
        this.cost = cost;
    }
}
