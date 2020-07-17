package com.gca.shipping.services;

import com.gca.shipping.dto.CostDto;

import java.math.BigDecimal;
import java.util.List;

public interface CostService {
    CostDto calc(List<BigDecimal> sum);
}
