package com.gca.catalogservice.logic.impl;

import com.gca.catalogservice.data.entities.Product;
import com.gca.catalogservice.data.dto.ProductDto;
import com.gca.catalogservice.data.repo.ProductRepository;
import com.gca.catalogservice.logic.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAll() {
        return this.repository.findAll()
                .stream()
                .map(this::getMappedProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) {
        Product product = this.repository.getOne(id);
        return getMappedProductDto(product);
    }

    @Override
    public List<ProductDto> getProductsByIds(List<Integer> productIds) {
        List<Product> productList = repository.findAllById(productIds);
        return productList.stream().map(this::getMappedProductDto).collect(Collectors.toList());
    }

    private ProductDto getMappedProductDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }
}
