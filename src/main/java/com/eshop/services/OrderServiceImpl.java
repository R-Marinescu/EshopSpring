package com.eshop.services;

import com.eshop.DTO.OrderDTO;
import com.eshop.DTO.UserDTO;
import com.eshop.models.Order;
import com.eshop.models.User;
import com.eshop.repositories.OrderRepo;
import com.eshop.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;

    private final UserRepo userRepo;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, UserService userService, UserRepo userRepo) {
        this.userRepo = userRepo;
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
        order.setUser(userService.convertDTOToUser(orderDTO.getUserDTO()));
        order.setOrderDate(Instant.now());

        return orderRepo.save(order);
    }

//    @Override
//    public Order createOrder(OrderDTO orderDTO) {
//        Order order = new Order();
//
//        // Fetch the user from the database based on the user ID in the orderDTO
//        //Optional<User> optionalUser = userRepo.findById(orderDTO.getUserDTO().getUserId());
//        Optional<UserDTO> optionalUser = userService.getUserById(orderDTO.getUserDTO().getUserId());
//
//        if (optionalUser.isPresent()) {
//            UserDTO existingUser = optionalUser.get();
//
//            // Associate the existing user with the order
//            order.setUser(existingUser);
//            order.setOrderDate(Instant.now());
//
//            return orderRepo.save(order);
//        } else {
//            // Handle the case where the user with the specified ID is not found
//            throw new EntityNotFoundException("User with ID " + orderDTO.getUserDTO().getUserId() + " not found");
//        }
//    }


    @Override
    public Order updateOrder(Integer orderId, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if(optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            if(orderDTO.getUserDTO() != null) {
                User user = userService.convertDTOToUser(orderDTO.getUserDTO());
                existingOrder.setUser(user);
            }
            orderRepo.save(existingOrder);
        } else{
            throw new EntityNotFoundException("Order with Id " + orderId + " not found");
        }
        return null;
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
