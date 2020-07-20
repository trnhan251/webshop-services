package com.gca.checkout.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

public class CreditCardDto {

    @CreditCardNumber(ignoreNonDigitCharacters = true)
    private String number;
    @Future
    @JsonFormat(pattern = "MM/yyyy")
    private Date expire;
    @Pattern(regexp = "\\d{3}")
    private String ccv;

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

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
}
