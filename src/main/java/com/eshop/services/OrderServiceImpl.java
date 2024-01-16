package com.eshop.services;

import com.eshop.models.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{
    @Override
    public Optional<Order> getOrderById(Integer orderId) {
        return Optional.empty();
    }

    @Override
    public Order createOrder(Order order) {
        return order;
    }

    @Override
    public void updateOrder(Integer orderId, Order order) {

    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Integer orderId) {

    }

    @Override
    public void deleteOrderById(Integer orderId) {

    }
}
