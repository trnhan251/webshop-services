package com.gca.catalog.runners;

import com.gca.catalog.entities.Product;
import com.gca.catalog.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class InitDbRunner implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

    private Logger logger = LoggerFactory.getLogger(InitDbRunner.class);

    @Override
    public void run(String... args) throws Exception {
        //repository.findAllById(new ArrayList<>(1,2,3,4,5,6,7,8,9,10)).size()
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Product p = new Product();

            p.setId(Integer.valueOf(i));
            p.setPrice(BigDecimal.valueOf(i * 10).add(BigDecimal.valueOf(i - 1).divide(BigDecimal.TEN))); // 10.00, 20.10, 30,20 ... 100,90
            p.setName("Product_Name_" + i);

            products.add(p);
        }

        repository.saveAll(products);
        repository.flush();
    }
}
