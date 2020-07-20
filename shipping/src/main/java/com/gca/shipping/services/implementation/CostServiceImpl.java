package com.gca.shipping.services.implementation;

import com.gca.shipping.dto.CostDto;
import com.gca.shipping.services.CostService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CostServiceImpl implements CostService {
    @Override
    public CostDto calc(List<BigDecimal> prices) {
        if (prices.isEmpty()){
            CostDto c = new CostDto();
            c.setTotal(BigDecimal.ZERO);
            c.setShipping(BigDecimal.ZERO);
            return c;
        }
        BigDecimal sum = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        CostDto costDto = new CostDto();
        costDto.setTotal(sum);

        if (BigDecimal.valueOf(100).compareTo(sum) == 1) {
            costDto.setShipping(costDto.getShipping().add(BigDecimal.TEN));
            costDto.setTotal(costDto.getTotal().add(BigDecimal.TEN));
        }

        return costDto;
    }
}
