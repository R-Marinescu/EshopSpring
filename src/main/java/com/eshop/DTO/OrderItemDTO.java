package com.eshop.DTO;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Integer orderItemId;

    private OrderDTO orderDTO;

    private ProductDTO productDTO;

    private Integer quantity;
    private BigDecimal price;

    public OrderItemDTO(Integer orderItemId, OrderDTO orderDTO, ProductDTO productDTO, Integer quantity, BigDecimal price) {
        this.orderItemId = orderItemId;
        this.orderDTO = orderDTO;
        this.productDTO = productDTO;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemDTO() {
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
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

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
