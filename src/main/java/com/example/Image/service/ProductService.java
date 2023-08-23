package com.example.Image.service;

import com.example.Image.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
}