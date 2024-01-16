package com.eshop.services;

import com.eshop.models.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    Optional<OrderItem> getOrderItemById(Integer orderItemId);
    List<OrderItem> getAllOrderItems();
    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(Integer orderItemId, OrderItem orderItem);
    void deleteOrderItem(Integer orderItemId);
}
