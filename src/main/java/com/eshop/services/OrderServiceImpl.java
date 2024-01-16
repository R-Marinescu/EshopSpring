package com.eshop.services;

import com.eshop.models.Order;
import com.eshop.repositories.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    final private OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepo.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public void updateOrder(Integer orderId, Order order) {
        Optional<Order> optionalOrder = getOrderById(orderId);

        if(optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            if(order.getUser() != null) {
                existingOrder.setUser(order.getUser());
            }
            orderRepo.save(existingOrder);
        } else{
            throw new EntityNotFoundException("Order with Id " + orderId + " not found");
        }
    }

    @Override
    public void deleteOrder(Integer orderId) {
        Optional<Order> optionalOrder = getOrderById(orderId);

        if(optionalOrder.isPresent()) {
            orderRepo.deleteById(orderId);

        }else{
            throw new EntityNotFoundException("Order with Id " + orderId + " not found");
        }
    }
}
