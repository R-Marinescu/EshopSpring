package com.eshop.services;

import com.eshop.models.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    Optional<ShoppingCart> getShoppingCartById(Integer cartId);
    List<ShoppingCart> getAllShoppingCarts();
    ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
    ShoppingCart updateShoppingCart(Integer cartId, ShoppingCart shoppingCart);
    void deleteShoppingCart();
}
