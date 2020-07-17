package com.gca.checkout.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Embeddable;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Embeddable
public class CreditCardDto {

    @CreditCardNumber(ignoreNonDigitCharacters = true)
    private String number;
    @Future
    @JsonFormat(pattern = "MM/yyyy")
    private Date expire;
    @PositiveOrZero
    @Max(value = 999)
    private Integer ccv;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Integer getCcv() {
        return ccv;
    }

    public void setCcv(Integer ccv) {
        this.ccv = ccv;
    }
}