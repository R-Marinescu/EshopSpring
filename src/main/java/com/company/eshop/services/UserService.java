package com.company.eshop.services;

import com.company.eshop.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Integer userId);
    void createUser(User user);
    void updateUser(Integer userId, User user);
    List<User> getAllUsers();
}
