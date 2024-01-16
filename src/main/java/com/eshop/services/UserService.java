package com.eshop.services;

import com.eshop.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Integer userId);
    User createUser(User user);
    void updateUser(Integer userId, User user);
    List<User> getAllUsers();
}