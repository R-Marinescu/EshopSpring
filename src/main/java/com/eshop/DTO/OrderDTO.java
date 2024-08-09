package com.eshop.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public class OrderDTO {
    private Integer orderId;
    private Integer userId;
    private Integer totalPrice;
    private Instant orderDate;
    private List<OrderItemDTO> orderItems;


    public OrderDTO() {
    }

    @JsonCreator
    public OrderDTO(
            @JsonProperty("orderId") Integer orderId,
            @JsonProperty("userId") Integer userId,
            @JsonProperty("totalPrice") Integer totalPrice,
            @JsonProperty("orderDate") Instant orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userDTO=" + userId +
                ", orderDate=" + orderDate +
                '}';
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
}
