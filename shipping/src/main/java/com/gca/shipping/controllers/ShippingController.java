package com.gca.shipping.controllers;

import com.gca.shipping.dto.ShippingOrderDto;
import com.gca.shipping.dto.TrackingShippingOrderDto;
import com.gca.shipping.services.ShippingService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.lang.NonNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;

@RestController
@RequestMapping("/")
@Validated()
public class ShippingController {


    private static final String RESILIENCE_INSTANCE_NAME = "shipping";

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @GetMapping(value = "/{trackingNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<TrackingShippingOrderDto>> getShippingOrder(
            @PathVariable(value = "trackingNumber")
                    String trackingNumber,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "[param(d)] must be inside the range of [0, 10000]")
            @Max(value = 10000, message = "[param(d)] must be inside the range of [0, 10000]")
                    Long d
    ) {
        return Mono
                .delay(Duration.ofMillis(d))
                .just(ResponseEntity.of(shippingService.getShippingOrder(trackingNumber)));
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<TrackingShippingOrderDto> postShippingOrder(
            @RequestBody
            @Valid
            @NonNull
                    ShippingOrderDto shippingOrderDto,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "[param(d)] must be inside the range of [0, 10000]")
            @Max(value = 10000, message = "[param(d)] must be inside the range of [0, 10000]")
                    Long d

    ) {

        return Mono
                .delay(Duration.ofMillis(d))
                .just(shippingService.addShippingOrder(shippingOrderDto));
    }

}
