package com.gca.cart.controllers;

import com.gca.cart.dto.CartDto;
import com.gca.cart.repositories.CartRepository;
import com.gca.cart.services.CartService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/")
@Validated()
public class CartController {

    @Autowired
    private CartRepository rep;

    private static final String RESILIENCE_INSTANCE_NAME = "cart";

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @GetMapping(value = {"/{id}", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CartDto> getCart(
            @PathVariable(value = "id")
                    Optional<Integer> id,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "[param(d)] must be inside the range of [0, 10000]")
            @Max(value = 10000, message = "[param(d)] must be inside the range of [0, 10000]")
                    Long d
    ) {
        return Mono
                .delay(Duration.ofMillis(d))
                .just(cartService.getCart(id.orElse(-1)));
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<CartDto> putCart(
            @PathVariable(value = "id")
                    Integer id,
            @RequestBody
            @NonNull
                    Set<Integer> prodcutIds,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "[param(d)] must be inside the range of [0, 10000]")
            @Max(value = 10000, message = "[param(d)] must be inside the range of [0, 10000]")
                    Long d

    ) {

        return Mono
                .delay(Duration.ofMillis(d))
                .just(cartService.putCart(id, prodcutIds));
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<CartDto> emptyCart(
            @PathVariable(value = "id")
                    Integer id,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "[param(d)] must be inside the range of [0, 10000]")
            @Max(value = 10000, message = "[param(d)] must be inside the range of [0, 10000]")
                    Long d

    ) {

        return Mono
                .delay(Duration.ofMillis(d))
                .just(cartService.emptyCart(id));
    }
}
