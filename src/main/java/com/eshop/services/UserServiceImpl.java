package com.eshop.services;

import com.eshop.models.User;
import com.eshop.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepo.findById(userId);
    }

    @Transactional
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public void updateUser(Integer userId, User user) {
        Optional<User> optionalUser = getUserById(userId);

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setUsername(user.getUsername());
            existingUser.setPhoneNumber(user.getPhoneNumber());
        }else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}