package com.eshop.services;

import com.eshop.models.Product;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{
    @Override
    public Optional<Product> getProductById(Integer productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Integer productId, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Integer productId) {

    }
}
