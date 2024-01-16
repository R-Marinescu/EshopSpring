package com.eshop.services;

import com.eshop.models.ShoppingCart;
import com.eshop.repositories.ShoppingCartRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepo shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Integer cartId) {
        return shoppingCartRepo.findById(cartId);
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepo.findAll();
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart updateShoppingCart(Integer cartId, ShoppingCart shoppingCart) {
        Optional<ShoppingCart> optionalShoppingCart = getShoppingCartById(cartId);

        if(optionalShoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = optionalShoppingCart.get();

            if (shoppingCart.getProduct() != null) {
                existingShoppingCart.setProduct(shoppingCart.getProduct());
            }

            if (shoppingCart.getQuantity() != null) {
                existingShoppingCart.setQuantity(shoppingCart.getQuantity());
            }
            shoppingCartRepo.save(existingShoppingCart);

            return existingShoppingCart;
        } else {
            throw new EntityNotFoundException("ShoppingCart with ID " + cartId + " not found");
        }
    }

    @Override
    public void deleteShoppingCart(Integer cartId) {
        Optional<ShoppingCart> optionalShoppingCart = getShoppingCartById(cartId);

        if(optionalShoppingCart.isPresent()){
            shoppingCartRepo.deleteById(cartId);
        }
    }
}
