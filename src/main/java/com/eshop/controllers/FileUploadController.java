package com.eshop.controllers;

import com.eshop.config.FileStorageService;
import com.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private final FileStorageService fileStorageService;
    private final ProductService productService;

    @Autowired
    public FileUploadController(FileStorageService fileStorageService, ProductService productService) {
        this.fileStorageService = fileStorageService;
        this.productService = productService;
    }

    @PostMapping("/product-image/{productId}")
    public ResponseEntity<String> uploadProductImage(@PathVariable Integer productId,
                                                     @RequestParam("file") MultipartFile file) {
        try {
            fileStorageService.saveFile(file);

            String fileName = file.getOriginalFilename();

            productService.updateProductImage(productId, fileName);

            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
