package com.company.eshop.services;

import com.company.eshop.models.User;
import com.company.eshop.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepo.findById(userId);
    }

    @Override
    public void createUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(Integer userId, User user) {
        Optional<User> optionalUser = getUserById(userId);

        if(optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setLastName(user.getLastName());
        }else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
