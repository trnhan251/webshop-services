package com.gca.catalogservice.rest;

import com.gca.catalogservice.data.dto.ProductDto;
import com.gca.catalogservice.data.repo.ProductRepository;
import com.gca.catalogservice.logic.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @GetMapping()
    ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.of(Optional.of(this.productService.getAll()));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        ProductDto productDto = this.productService.getProductById(id);
        return productDto == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(productDto);
    }

    @PostMapping("/product")
    ResponseEntity<List<ProductDto>> getProductsByIds(@RequestBody List<Integer> ids) {
        List<ProductDto> productDtos = this.productService.getProductsByIds(ids);
        return productDtos == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(productDtos);
    }
}
