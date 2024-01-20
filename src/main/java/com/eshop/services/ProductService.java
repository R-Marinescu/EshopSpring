package com.eshop.services;

import com.eshop.DTO.ProductDTO;
import com.eshop.models.Product;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(Integer productId);
    List<ProductDTO> getAllProducts();
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Integer productId, ProductDTO productDTO);
    void deleteProduct(Integer productId);
    public ProductDTO convertProductToDTO(Product product);
    public Product convertDTOToProduct(ProductDTO productDTO);
}
