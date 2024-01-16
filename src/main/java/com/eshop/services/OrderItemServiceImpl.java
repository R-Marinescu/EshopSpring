package com.eshop.services;

import com.eshop.models.OrderItem;

import java.util.List;
import java.util.Optional;

public class OrderItemServiceImpl implements OrderItemService{


    @Override
    public Optional<OrderItem> getOrderItemById(Integer orderItemId) {
        return Optional.empty();
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return null;
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem updateOrderItem(Integer orderItemId, OrderItem orderItem) {
        return null;
    }

    @Override
    public void deleteOrderItem(Integer orderItemId) {

    }
}
