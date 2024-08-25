package com.eshop.services;

import com.eshop.DTO.ProductDTO;
import com.eshop.models.Product;
import com.eshop.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductDTO convertProductToDTO(Product product) {
        String imageUrl = baseUrl + "/images/" + new File(product.getImage()).getName();
        product.setImage(imageUrl);

        return new ProductDTO(product.getId(), product.getProductName(), product.getPrice(), product.getStockQuantity(), product.getCategory(), product.getImage());
    }

    public Product convertDTOToProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getProductId(),
                productDTO.getProductName(),
                productDTO.getPrice(),
                productDTO.getStockQuantity(),
                productDTO.getCategory(),
                productDTO.getImage());
    }

    @Override
    public ProductDTO getProductById(Integer productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            String imageUrl = baseUrl + "/images/" + new File(product.getImage()).getName();
            product.setImage(imageUrl);

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
        product.setCategory(productDTO.getCategory());
        product.setImage(productDTO.getImage());

        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Integer productId, ProductDTO productDTO) {
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

            if(productDTO.getCategory() != null) {
                existingProduct.setCategory(productDTO.getCategory());
            }

            if(productDTO.getImage() != null) {
                existingProduct.setImage(productDTO.getImage());
            }

            productRepo.save(existingProduct);

        }else {
            throw new EntityNotFoundException("Product with Id " + productId + " not found");
        }
        return null;
    }

    public void updateProductImage(Integer productId, String imageName) {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setImage(imageName);
            productRepo.save(existingProduct);
        } else {
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
