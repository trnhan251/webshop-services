package com.gca.shipping.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@Configuration
@Order(-2)
public class GlobalErrorHandler extends DefaultErrorAttributes {

    private Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Throwable err = getError(request);

        logger.error("Bad Request");
        System.out.println(err.getClass().getName());
        if (err instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) err;

        } else if (err instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) err;

        } else if (err instanceof MethodNotAllowedException) {
            MethodNotAllowedException methodNotAllowedException = (MethodNotAllowedException) err;
        } else if (err instanceof TimeoutException) {

        }


        return super.getErrorAttributes(request, options);
    }


}
