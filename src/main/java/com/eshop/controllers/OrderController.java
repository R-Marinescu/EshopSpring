package com.eshop.controllers;

import com.eshop.DTO.OrderDTO;
import com.eshop.models.Order;
import com.eshop.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Integer orderId) {
        Optional<OrderDTO> optionalOrderDTO = orderService.getOrderById(orderId);

        if (optionalOrderDTO.isPresent()) {
            OrderDTO orderDTO = optionalOrderDTO.get();

            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/create")
//    ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        System.out.println("edw?" + order);
//        try {
//            Order createdOrder = orderService.createOrder(order);
//            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/create")
    ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println(orderDTO);
        try {
            Order createOrder = orderService.createOrder(orderDTO);
            return new ResponseEntity<>(createOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/update/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable Integer orderId) {
        try {
            Order updatedOrder = orderService.updateOrder(orderId, orderDTO);
            return new ResponseEntity<>(updatedOrder, HttpStatus.CREATED);
        }catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
