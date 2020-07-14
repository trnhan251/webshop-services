package com.gca.catalogservice.data.repo;

import com.gca.catalogservice.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
