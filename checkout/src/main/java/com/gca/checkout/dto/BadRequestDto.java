package com.gca.checkout.dto;

import java.util.Map;

public class BadRequestDto {

    private Map<String, String> validationFailures;

    public Map<String, String> getValidationFailures() {
        return validationFailures;
    }

    public void setValidationFailures(Map<String, String> validationFailures) {
        this.validationFailures = validationFailures;
    }

    public BadRequestDto(Map<String, String> validationFailures) {
        this.validationFailures = validationFailures;
    }
}
