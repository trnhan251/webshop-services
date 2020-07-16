package com.gca.catalog.services;

import com.gca.catalog.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getAll();
    Optional<ProductDto> getProductById(int id);
}
