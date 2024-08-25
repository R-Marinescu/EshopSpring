package com.eshop.controllers;

import com.eshop.DTO.ProductDTO;
import com.eshop.models.Product;
import com.eshop.services.ProductService;
import com.eshop.config.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @Autowired
    public AdminController(ProductService productService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestParam("productName") String productName,
                                                 @RequestParam("price") BigDecimal price,
                                                 @RequestParam("stockQuantity") int stockQuantity,
                                                 @RequestParam("category") String category,
                                                 @RequestParam("image") MultipartFile image) {
        try {
            String imageName = fileStorageService.saveFile(image);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(productName);
            productDTO.setPrice(price);
            productDTO.setStockQuantity(stockQuantity);
            productDTO.setCategory(category);
            productDTO.setImage(imageName);

            Product createdProduct = productService.createProduct(productDTO);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/updateProduct/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestParam(value = "productName", required = false) String productName,
                                                 @RequestParam(value = "price", required = false) BigDecimal price,
                                                 @RequestParam(value = "stockQuantity", required = false) Integer stockQuantity,
                                                 @RequestParam(value = "category", required = false) String category,
                                                 @RequestParam(value = "image", required = false) MultipartFile image) {
        System.out.println("here");
        try {
            String imageName = null;
            if (image != null && !image.isEmpty()) {
                System.out.println("1");
                imageName = fileStorageService.saveFile(image);
            }
            System.out.println("2");
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(productName);
            productDTO.setPrice(price);
            productDTO.setStockQuantity(stockQuantity);
            productDTO.setCategory(category);
            productDTO.setImage(imageName);

            System.out.println(productDTO);

            Product updatedProduct = productService.updateProduct(productId, productDTO);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
