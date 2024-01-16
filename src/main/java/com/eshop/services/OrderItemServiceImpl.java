package com.eshop.services;

import com.eshop.models.OrderItem;
import com.eshop.repositories.OrderItemRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Integer orderItemId) {
        return orderItemRepo.findById(orderItemId);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepo.findAll();
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Integer orderItemId, OrderItem orderItem) {
        Optional<OrderItem> optionalOrderItem = getOrderItemById(orderItemId);

        if(optionalOrderItem.isPresent()) {
            OrderItem existingOrderItem = optionalOrderItem.get();

            if(orderItem.getProduct() != null) {
                existingOrderItem.setProduct(orderItem.getProduct());
            }
            if(orderItem.getQuantity() != null) {
                existingOrderItem.setQuantity(orderItem.getQuantity());
            }
            orderItemRepo.save(existingOrderItem);

            return existingOrderItem;
        }else {
            throw new EntityNotFoundException("OrderItem with Id " + orderItemId + " not found");
        }
    }

    @Override
    public void deleteOrderItem(Integer orderItemId) {
        Optional<OrderItem> optionalOrderItem = getOrderItemById(orderItemId);

        if(optionalOrderItem.isPresent()) {
            orderItemRepo.deleteById(orderItemId);
        }else {
            throw new EntityNotFoundException("OrderItem with Id " + orderItemId + " not found");
        }

    }
}
