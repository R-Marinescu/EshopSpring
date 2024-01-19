package com.eshop.DTO;

public class OrderItemDTO {
    private Integer orderItemId;

    private OrderDTO orderDTO;

    private ProductDTO productDTO;

    private Integer quantity;

    public OrderItemDTO(Integer orderItemId, OrderDTO orderDTO, ProductDTO productDTO, Integer quantity) {
        this.orderItemId = orderItemId;
        this.orderDTO = orderDTO;
        this.productDTO = productDTO;
        this.quantity = quantity;
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
}
