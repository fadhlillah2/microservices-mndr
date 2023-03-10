package com.komodo.productservice.service;

import com.komodo.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product post(Product user);

    List<Product> getAllProduct();
    Product update(Long id, Product product);

    void delete(Long id);
}
