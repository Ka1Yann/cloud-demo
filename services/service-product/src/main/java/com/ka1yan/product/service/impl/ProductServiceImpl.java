package com.ka1yan.product.service.impl;

import com.ka1yan.product.entity.Product;
import com.ka1yan.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("iPhone17 Pro Max");
        product.setPrice(BigDecimal.valueOf(11999));
        product.setNum(2);
        return product;
    }
}
