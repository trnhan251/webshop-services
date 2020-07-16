package com.gca.catalog.controllers;

import com.gca.catalog.dto.ProductDto;
import com.gca.catalog.services.DelayService;
import com.gca.catalog.services.ProductService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@RestController
@RequestMapping("/")
@Validated()
public class CatalogController {

    private final ProductService productService;
    private final DelayService delayService;

    public CatalogController(ProductService productService, DelayService delayService) {
        this.productService = productService;
        this.delayService = delayService;
    }

    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gibt ein Array aller Produkte wieder")
    List<ProductDto> getAllProducts(
            @RequestParam(value = "d", defaultValue = "0")
            @ApiParam(value = "Zeit in Millisekunden um die eine Anfrage verzögert wird", name = "d", required = false,allowableValues = "range[0,10000]")
            @Min(value = 0, message = DelayService.MESSAGE)
            @Max(value = 10000, message = DelayService.MESSAGE)
                    long d
    ) {
        delayService.delay(d);
        return this.productService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gibt das Produkte mit der passenden `id` wieder")
    ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        Optional<ProductDto> productDto = this.productService.getProductById(id);
        return productDto.isPresent() ? ResponseEntity.ok(productDto.get()) : ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public void constraintViolationHandler() {
    }
}
