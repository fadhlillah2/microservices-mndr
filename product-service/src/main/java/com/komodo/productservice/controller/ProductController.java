package com.komodo.productservice.controller;

import com.komodo.productservice.entity.Product;
import com.komodo.productservice.service.ProductService;
import com.komodo.productservice.service.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/tes")
    public String hello(){
        return "welcome to springboot";
    }

    @PostMapping("")
    public Product post(@RequestBody Product product){
        return productServiceImpl.post(product);
    }

    @GetMapping("")
    public List<Product> getAllProduct(){
        List<Product> list = productServiceImpl.getAllProduct();
        return list;
    }

    @PutMapping("{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product){
        return productServiceImpl.update(id,product);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id){
        productServiceImpl.delete(id);
        return "Succes deleted data";
    }

}
