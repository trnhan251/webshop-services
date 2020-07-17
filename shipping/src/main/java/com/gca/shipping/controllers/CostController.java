package com.gca.shipping.controllers;

import com.gca.shipping.dto.BadRequestDto;
import com.gca.shipping.dto.CostDto;
import com.gca.shipping.repositories.ShippingRepository;
import com.gca.shipping.services.CostService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Validated()
public class CostController {

    @Autowired
    private ShippingRepository rep;

    private static final String RESILIENCE_INSTANCE_NAME = "cost";

    private final CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @PostMapping(value = "/cost", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CostDto> getCart(
            @RequestBody
                    List<@PositiveOrZero BigDecimal> prices,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "[param(d)] must be inside the range of [0, 10000]")
            @Max(value = 10000, message = "[param(d)] must be inside the range of [0, 10000]")
                    Long d
    ) {
        return Mono
                .delay(Duration.ofMillis(d))
                .just(costService.calc(prices));
    }
}
