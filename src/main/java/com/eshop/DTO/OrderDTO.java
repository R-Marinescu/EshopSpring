package com.eshop.DTO;

import java.time.Instant;

public class OrderDTO {
    private Integer orderId;
    private UserDTO userDto;
    private Instant orderDate;

    public OrderDTO() {
    }

    public OrderDTO(Integer orderId, UserDTO userDto, Instant orderDate) {
        this.orderId = orderId;
        this.userDto = userDto;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }
}
