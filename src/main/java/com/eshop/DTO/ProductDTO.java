package com.eshop.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer productId;

    private String productName;

    private BigDecimal price;

    private Integer stockQuantity;

    private String category;

    private String image;

    @JsonCreator
    public ProductDTO(
            @JsonProperty("productId") Integer productId,
            @JsonProperty("productName") String productName,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("stockQuantity") Integer stockQuantity,
            @JsonProperty("category") String category,
            @JsonProperty("image") String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.image = image;
    }

    public ProductDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
