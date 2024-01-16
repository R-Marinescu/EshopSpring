package com.eshop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {
    @Id
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}