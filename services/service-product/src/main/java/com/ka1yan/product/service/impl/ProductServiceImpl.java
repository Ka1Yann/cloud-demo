package com.ka1yan.product.service.impl;

import com.ka1yan.product.entity.Product;
import com.ka1yan.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final Environment environment;

    public ProductServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("iPhone17 Pro Max");
        product.setPrice(BigDecimal.valueOf(11999));
        product.setNum(2);
        log.info("===>Port:{}:Get product by id:{}", environment.getProperty("server.port"), id);
        return product;
    }
}
