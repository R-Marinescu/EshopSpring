package com.eshop.services;

import com.eshop.DTO.ShoppingCartDTO;
import com.eshop.models.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCartDTO> getShoppingCartById(Integer cartId);
    List<ShoppingCartDTO> getAllShoppingCarts();
    ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartDTO);
    ShoppingCart updateShoppingCart(Integer cartId, ShoppingCartDTO shoppingCartDTO);
    void deleteShoppingCart(Integer cartId);
}
