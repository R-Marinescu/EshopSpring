package com.eshop.services;

import com.eshop.DTO.UserDTO;
import com.eshop.models.User;
import com.eshop.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDTO convertUserToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPhoneNumber());
    }

    public User convertDTOToUser(UserDTO userDTO) {
        return new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getPhoneNumber()
        );
    }

    @Override
    public Optional<UserDTO> getUserById(Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);

        return optionalUser.map(this::convertUserToDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return userRepo.save(user);
    }

    @Override
    public User updateUser(Integer userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepo.findById(userId);
        User existingUser;

        if (optionalUser.isPresent()) {
            existingUser = optionalUser.get();

            if (userDTO.getFirstName() != null) {
                existingUser.setFirstName(userDTO.getFirstName());
            }

            if (userDTO.getLastName() != null) {
                existingUser.setLastName(userDTO.getLastName());
            }

            if (userDTO.getUsername() != null) {
                existingUser.setUsername(userDTO.getUsername());
            }

            if (userDTO.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(userDTO.getPhoneNumber());
            }

        } else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isPresent()) {
            userRepo.deleteById(userId);
        } else {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }
    }
}
