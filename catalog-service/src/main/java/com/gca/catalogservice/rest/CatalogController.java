package com.gca.catalogservice.rest;

import com.gca.catalogservice.data.dto.ProductDto;
import com.gca.catalogservice.data.repo.ProductRepository;
import com.gca.catalogservice.logic.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    private final ProductService productService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);
    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @GetMapping()
    ResponseEntity<List<ProductDto>> getAllProducts() {
        LOGGER.info("GET - All Products");
        return ResponseEntity.of(Optional.of(this.productService.getAll()));
    }

    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        LOGGER.info("GET - Product By ID " + id);
        ProductDto productDto = this.productService.getProductById(id);
        return productDto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(productDto);
    }

    @CrossOrigin
    @PostMapping("/product")
    ResponseEntity<List<ProductDto>> getProductsByIds(@RequestBody List<Integer> ids) {
        LOGGER.info("POST - Products By List of IDs");
        List<ProductDto> productDtos = this.productService.getProductsByIds(ids);
        return productDtos == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(productDtos);
    }
}
