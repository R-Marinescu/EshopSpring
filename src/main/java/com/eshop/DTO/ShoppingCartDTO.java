package com.eshop.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCartDTO {
    private Integer cartId;

    private UserDTO userDTO;

    private ProductDTO productDTO;

    private Integer quantity;

    @JsonCreator
    public ShoppingCartDTO(
            @JsonProperty("cartId") Integer cartId,
            @JsonProperty("userDTO") UserDTO userDTO,
            @JsonProperty("productDto") ProductDTO productDto,
            @JsonProperty("quantity") Integer quantity) {
        this.cartId = cartId;
        this.userDTO = userDTO;
        this.productDTO = productDto;
        this.quantity = quantity;
    }

    public ShoppingCartDTO() {
    }

    public Integer getCartId() {
        return cartId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "cartId=" + cartId +
                ", userDTO=" + userDTO +
                ", productDTO=" + productDTO +
                ", quantity=" + quantity +
                '}';
    }
}
