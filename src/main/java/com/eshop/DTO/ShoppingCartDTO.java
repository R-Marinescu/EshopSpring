package com.eshop.DTO;

public class ShoppingCartDTO {
    private Integer cartId;

    private UserDTO userDTO;

    private ProductDTO productDTO;

    private Integer quantity;

    public ShoppingCartDTO(UserDTO userDTO, ProductDTO productDto, Integer quantity) {
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
}
