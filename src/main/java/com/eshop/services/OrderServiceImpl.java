package com.eshop.services;

import com.eshop.DTO.OrderDTO;
import com.eshop.DTO.UserDTO;
import com.eshop.models.Order;
import com.eshop.models.OrderItem;
import com.eshop.models.Product;
import com.eshop.models.User;
import com.eshop.repositories.OrderRepo;
import com.eshop.repositories.ProductRepo;
import com.eshop.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final UserService userService;
    private final ProductRepo productRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, UserService userService, UserRepo userRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    private OrderDTO convertOrderToDTO(Order order) {
        UserDTO userDTO = userService.convertUserToDTO(order.getUser());
        return new OrderDTO(order.getOrderId(), userDTO.getUserId(), order.getTotalPrice(), order.getOrderDate());
    }

    @Override
    public Optional<OrderDTO> getOrderById(Integer orderId) {
        return orderRepo.findById(orderId).map(this::convertOrderToDTO);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll()
                .stream()
                .map(this::convertOrderToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        logger.debug("Creating order for user ID: {}", orderDTO.getUserId());

        User user = userRepo.findById(orderDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + orderDTO.getUserId() + " not found"));

        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setTotalPrice(orderDTO.getTotalPrice());
        newOrder.setOrderDate(Instant.now());

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(itemDto -> {
                    Product product = productRepo.findById(itemDto.getProductDTO().getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product with ID " + itemDto.getProductDTO().getProductId() + " not found"));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setPrice(itemDto.getPrice());
                    orderItem.setOrder(newOrder);

                    return orderItem;
                })
                .collect(Collectors.toList());

        newOrder.setOrderItems(orderItems);

        return orderRepo.save(newOrder);
    }

    @Override
    public Order updateOrder(Integer orderId, OrderDTO orderDTO) {
        logger.debug("Updating order with ID: {}", orderId);

        Order existingOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found"));

        if (orderDTO.getUserId() != null) {
            User user = userRepo.findById(orderDTO.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with ID " + orderDTO.getUserId() + " not found"));
            existingOrder.setUser(user);
        }

        if (orderDTO.getTotalPrice() != null) {
            existingOrder.setTotalPrice(orderDTO.getTotalPrice());
        }
        if (orderDTO.getOrderDate() != null) {
            existingOrder.setOrderDate(orderDTO.getOrderDate());
        }

        List<OrderItem> updatedOrderItems = orderDTO.getOrderItems().stream()
                .map(itemDto -> {
                    Product product = productRepo.findById(itemDto.getProductDTO().getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product with ID " + itemDto.getProductDTO().getProductId() + " not found"));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setPrice(itemDto.getPrice());
                    orderItem.setOrder(existingOrder);

                    return orderItem;
                })
                .collect(Collectors.toList());

        existingOrder.setOrderItems(updatedOrderItems);

        return orderRepo.save(existingOrder);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        logger.debug("Deleting order with ID: {}", orderId);

        if (orderRepo.existsById(orderId)) {
            orderRepo.deleteById(orderId);
        } else {
            throw new EntityNotFoundException("Order with ID " + orderId + " not found");
        }
    }
}
