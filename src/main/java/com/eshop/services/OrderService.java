package com.eshop.services;

import com.eshop.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrderById(Integer orderId);
    Order createOrder(Order order);
    void updateOrder(Integer orderId, Order order);
    List<Order> getAllOrders();
    void deleteOrder(Integer orderId);
}
