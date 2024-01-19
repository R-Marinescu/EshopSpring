package com.eshop.services;

import com.eshop.DTO.ProductDTO;
import com.eshop.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO getProductById(Integer productId);
    List<ProductDTO> getAllProducts();
    Product createProduct(ProductDTO productDTO);
    void updateProduct(Integer productId, ProductDTO productDTO);
    void deleteProduct(Integer productId);
}
