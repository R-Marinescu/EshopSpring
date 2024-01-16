package com.eshop.services;

import com.eshop.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Integer productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    void updateProduct(Integer productId, Product product);
    void deleteProduct(Integer productId);
}
