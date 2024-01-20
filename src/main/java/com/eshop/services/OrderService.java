package com.eshop.services;

import com.eshop.DTO.OrderDTO;
import com.eshop.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<OrderDTO> getOrderById(Integer orderId);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Integer orderId, OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    void deleteOrder(Integer orderId);
}
