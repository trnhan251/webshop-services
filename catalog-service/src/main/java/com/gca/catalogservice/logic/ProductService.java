package com.gca.catalogservice.logic;

import com.gca.catalogservice.data.dto.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getProductById(int id);
    List<ProductDto> getProductsByIds(List<Integer> productIds);
}
