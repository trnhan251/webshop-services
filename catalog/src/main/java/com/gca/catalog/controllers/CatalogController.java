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
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@Validated()
public class CatalogController {

    @Autowired
    private ProductRepository rep;

    private static final String RESILIENCE_INSTANCE_NAME = "catalog";

    private final ProductService productService;
    private final DelayService delayService;

    public CatalogController(ProductService productService, DelayService delayService) {
        this.productService = productService;
        this.delayService = delayService;
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<ProductDto>> getAllProducts(
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = DelayService.MESSAGE)
            @Max(value = 10000, message = DelayService.MESSAGE)
                    Long d
    ) {
        return Mono
                .just(productService.getAll())
                .delayElement(Duration.ofMillis(d));
    }

    @Retry(name = RESILIENCE_INSTANCE_NAME)
    @CircuitBreaker(name = RESILIENCE_INSTANCE_NAME)
    @RateLimiter(name = RESILIENCE_INSTANCE_NAME)
    @TimeLimiter(name = RESILIENCE_INSTANCE_NAME)
    @Bulkhead(name = RESILIENCE_INSTANCE_NAME)
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<ProductDto>> getProductById(
            @PathVariable
                    int id,
            @RequestParam(value = "d", defaultValue = "0")
            @Min(value = 0, message = DelayService.MESSAGE)
            @Max(value = 10000, message = DelayService.MESSAGE)
                    Long d
    ) {

        Optional<ProductDto> productDto = this.productService.getProductById(id);
        ResponseEntity<ProductDto> re = productDto.isPresent() ? ResponseEntity.ok(productDto.get()) : ResponseEntity.notFound().build();
        return Mono
                .just(re)
                .delayElement(Duration.ofMillis(d));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public void constraintViolationHandler(ConstraintViolationException e) {
    }
}
