package com.eshop.services;

import com.eshop.models.Product;
import com.eshop.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        return productRepo.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void updateProduct(Integer productId, Product product) {
        Optional<Product> optionalProduct= getProductById(productId);

        if(optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            if(product.getProductName() != null) {
                existingProduct.setProductName(product.getProductName());
            }

            if(product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }

            if(product.getStockQuantity() != null) {
                existingProduct.setStockQuantity(product.getStockQuantity());
            }

            productRepo.save(existingProduct);
        }else {
            throw new EntityNotFoundException("Product with Id " + productId + " not found");
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        Optional<Product> optionalProduct = getProductById(productId);

        if(optionalProduct.isPresent()) {
            productRepo.deleteById(productId);
        }else {
            throw new EntityNotFoundException("Product with Id " + productId + " not found");
        }
    }
}
