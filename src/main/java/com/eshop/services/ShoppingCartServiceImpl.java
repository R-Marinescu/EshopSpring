package com.eshop.services;

import com.eshop.models.ShoppingCart;

import java.util.List;
import java.util.Optional;

public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Override
    public Optional<ShoppingCart> getShoppingCartById(Integer cartId) {
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return null;
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public ShoppingCart updateShoppingCart(Integer cartId, ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public void deleteShoppingCart() {

    }
}
