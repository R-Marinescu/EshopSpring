package com.eshop.controllers;

import com.eshop.DTO.ProductDTO;
import com.eshop.config.FileStorageService;
import com.eshop.models.Product;
import com.eshop.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @Autowired
    public ProductController(ProductService productService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) {
        try {
            ProductDTO productDTO = productService.getProductById(productId);

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/create")
//    ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
//        Product createdProduct = productService.createProduct(productDTO);
//        System.out.println(createdProduct);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/update/{productId}")
//    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer productId) {
//        try {
//            Product updatedProduct = productService.updateProduct(productId, productDTO);
//            return new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);
//        }catch (Exception e) {
//
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PostMapping("/create")
//    public ResponseEntity<Product> createProduct(@RequestParam("productName") String productName,
//                                                 @RequestParam("price") int price,
//                                                 @RequestParam("stockQuantity") int stockQuantity,
//                                                 @RequestParam("image") MultipartFile image) {
//        try {
//            String imageName = fileStorageService.saveFile(image); // Save the image and get its name/path
//
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setProductName(productName);
//            productDTO.setPrice(BigDecimal.valueOf(price));
//            productDTO.setStockQuantity(stockQuantity);
//            productDTO.setImage(imageName); // Set the image name/path in the DTO
//
//            Product createdProduct = productService.createProduct(productDTO);
//            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/update/{productId}")
//    public ResponseEntity<Product> updateProduct(@RequestParam("productName") String productName,
//                                                 @RequestParam("price") int price,
//                                                 @RequestParam("stockQuantity") int stockQuantity,
//                                                 @RequestParam(value = "image", required = false) MultipartFile image,
//                                                 @PathVariable Integer productId) {
//        try {
//            String imageName = null;
//            if (image != null && !image.isEmpty()) {
//                imageName = fileStorageService.saveFile(image); // Save the image if provided
//            }
//
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setProductName(productName);
//            productDTO.setPrice(BigDecimal.valueOf(price));
//            productDTO.setStockQuantity(stockQuantity);
//            productDTO.setImage(imageName); // Set the image name/path in the DTO
//
//            Product updatedProduct = productService.updateProduct(productId, productDTO);
//            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
