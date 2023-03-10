package com.komodo.productservice.service.implementation;


import com.komodo.productservice.entity.Product;
import com.komodo.productservice.repository.ProductRepository;
import com.komodo.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;


    public Product post(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product update(Long id, Product product) {
        var usrDB = productRepository.findById(id);
        if(usrDB.isPresent()){
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
