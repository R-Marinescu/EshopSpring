package com.eshop.services;

import com.eshop.DTO.OrderDTO;
import com.eshop.DTO.UserDTO;
import com.eshop.models.Order;
import com.eshop.models.User;
import com.eshop.repositories.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, UserService userService) {
        this.orderRepo = orderRepo;
        this.userService = userService;
    }

    private OrderDTO convertOrderToDTO(Order order) {
        UserDTO userDTO = userService.convertUserToDTO(order.getUser());
        return new OrderDTO(order.getOrderId(), userDTO, order.getOrderDate());
    }

    @Override
    public Optional<OrderDTO> getOrderById(Integer orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        return optionalOrder.map(this::convertOrderToDTO);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(this::convertOrderToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(userService.convertDTOToUser(orderDTO.getUserDto()));

        return orderRepo.save(order);
    }

    @Override
    public void updateOrder(Integer orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if(optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            if(orderDTO.getUserDto() != null) {
                User user = userService.convertDTOToUser(orderDTO.getUserDto());
                existingOrder.setUser(user);
            }
            orderRepo.save(existingOrder);
        } else{
            throw new EntityNotFoundException("Order with Id " + orderId + " not found");
        }
    }

    @Override
    public void deleteOrder(Integer orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if(optionalOrder.isPresent()) {
            orderRepo.deleteById(orderId);

        }else{
            throw new EntityNotFoundException("Order with Id " + orderId + " not found");
        }
    }
}
