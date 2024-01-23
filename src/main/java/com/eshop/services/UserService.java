package com.eshop.services;

import com.eshop.DTO.UserDTO;
import com.eshop.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO convertUserToDTO(User user);
    User convertDTOToUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(Integer userId);
    User createUser(User user);
    User updateUser(Integer userId, UserDTO userDTO);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer userId);
}
