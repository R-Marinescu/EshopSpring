package com.eshop.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class OrderDTO {
    private Integer orderId;
    private UserDTO userDTO;
    private Instant orderDate;

    public OrderDTO() {
    }

    @JsonCreator
    public OrderDTO(
            @JsonProperty("orderId") Integer orderId,
            @JsonProperty("userDTO") UserDTO userDTO,
            @JsonProperty("orderDate") Instant orderDate) {
        this.orderId = orderId;
        this.userDTO = userDTO;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDTO = userDto;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userDTO=" + userDTO +
                ", orderDate=" + orderDate +
                '}';
    }
}
