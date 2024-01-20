package com.eshop.controllers;

import com.eshop.DTO.ShoppingCartDTO;
import com.eshop.models.ShoppingCart;
import com.eshop.services.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCartDTO> getCartById(@PathVariable Integer cartId) {
        Optional<ShoppingCartDTO> optionalShoppingCartDTO = shoppingCartService.getShoppingCartById(cartId);

        if(optionalShoppingCartDTO.isPresent()) {
            ShoppingCartDTO shoppingCartDTO = optionalShoppingCartDTO.get();

            return new ResponseEntity<>(shoppingCartDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCartDTO);
        return new ResponseEntity<>(createdShoppingCart, HttpStatus.CREATED);
    }

    @PostMapping("/update/{cartId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO, @PathVariable Integer cartId) {
        try {
            ShoppingCart updatedShoppingCart = shoppingCartService.updateShoppingCart(cartId, shoppingCartDTO);
            return new ResponseEntity<>(updatedShoppingCart, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable Integer cartId) {
        try {
            shoppingCartService.deleteShoppingCart(cartId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
