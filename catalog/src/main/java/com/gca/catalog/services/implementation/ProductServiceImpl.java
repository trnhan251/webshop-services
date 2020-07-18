package com.gca.catalog.services.implementation;

import com.gca.catalog.dto.ProductDto;
import com.gca.catalog.entities.Product;
import com.gca.catalog.repositories.ProductRepository;
import com.gca.catalog.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public Optional<ProductDto> getProductById(int id) {
        Optional<Product> product = this.repository.findById(id);
        return  product.isPresent() ? Optional.of(getMappedProductDto(product.get())) : Optional.empty();
    }

    @Override
    public List<ProductDto> collect(List<Integer> ids) {
        return repository.findAllById(ids).stream()
        .map(this::getMappedProductDto)
        .collect(Collectors.toList());
    }

    private ProductDto getMappedProductDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
