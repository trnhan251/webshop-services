package com.gca.catalog.services;

import java.util.Optional;

public interface DelayService {
    static final String MESSAGE = "'d' must be in the range from [0, 10000]";
    void delay(Optional<Long> milliseconds);
}
