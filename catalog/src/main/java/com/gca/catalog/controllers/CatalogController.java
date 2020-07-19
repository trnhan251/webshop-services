package com.gca.catalog.controllers;

import com.gca.catalog.dto.ProductDto;
import com.gca.catalog.repositories.ProductRepository;
import com.gca.catalog.services.DelayService;
import com.gca.catalog.services.ProductService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
@Validated()
public class CatalogController {

    public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
    public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
    public static final String METHODS_NAME = "Access-Control-Allow-Methods";
    public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
    public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

    @Autowired
    private ProductRepository rep;

    private static final String RESILIENCE_INSTANCE_NAME = "catalog";

    private final ProductService productService;
    private final DelayService delayService;

    public CatalogController(ProductService productService, DelayService delayService) {
        this.productService = productService;
        this.delayService = delayService;
    }

    @CrossOrigin(allowCredentials = "true")
    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<ProductDto>> getAllProducts(
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "DelayService.MESSAGE")
            @Max(value = 10000, message = "DelayService.MESSAGE")
                    Long d
    ) {
        return Mono
                .delay(Duration.ofMillis(d))
                .just(productService.getAll());
    }

    @CrossOrigin
    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<Optional<ProductDto>> getProductById(
            @PathVariable
                    int id,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "DelayService.MESSAGE")
            @Max(value = 10000, message = "DelayService.MESSAGE")
                    Long d
    ) {
        return Mono
                .delay(Duration.ofMillis(d))
                .just(productService.getProductById(id));
    }

    @CrossOrigin(allowCredentials = "true")
    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @PostMapping(value = "/collect", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<List<ProductDto>> collectProductsById(
            @RequestBody
            @NotNull
                    List<@NotNull Integer> ids,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = "...")
            @Max(value = 10000, message = "...")
                    Long d
    ) {

        return Mono
                .delay(Duration.ofMillis(d))
                .just(this.productService.collect(ids));
    }
}
