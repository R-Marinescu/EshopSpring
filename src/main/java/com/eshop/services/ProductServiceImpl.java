package com.eshop.services;

import com.eshop.DTO.ProductDTO;
import com.eshop.models.Product;
import com.eshop.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    private ProductDTO convertProductToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getProductName(), product.getPrice(), product.getStockQuantity());
    }

    @Override
    public ProductDTO getProductById(Integer productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            return convertProductToDTO(product);
        } else {
            throw new EntityNotFoundException("Product with ID " + productId + " not found");
        }

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(this::convertProductToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        return productRepo.save(product);
    }

    @Override
    public void updateProduct(Integer productId, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            if(productDTO.getProductName() != null) {
                existingProduct.setProductName(productDTO.getProductName());
            }

            if(productDTO.getPrice() != null) {
                existingProduct.setPrice(productDTO.getPrice());
            }

            if(productDTO.getStockQuantity() != null) {
                existingProduct.setStockQuantity(productDTO.getStockQuantity());
            }

            productRepo.save(existingProduct);

        }else {
            throw new EntityNotFoundException("Product with Id " + productId + " not found");
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isPresent()) {
            productRepo.deleteById(productId);
        }else {
            throw new EntityNotFoundException("Product with Id " + productId + " not found");
        }
    }
}
