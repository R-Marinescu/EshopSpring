package com.eshop.services;

import com.eshop.models.UserRole;
import com.eshop.repositories.UserRolesRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    private final UserRolesRepo userRolesRepo;

    @Autowired
    public UserRoleServiceImpl(UserRolesRepo userRolesRepo) {
        this.userRolesRepo = userRolesRepo;
    }

    @Override
    public Optional<UserRole> getUserRoleById(Integer userRoleId) {
        return userRolesRepo.findById(userRoleId);
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRolesRepo.findAll();
    }

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return userRolesRepo.save(userRole);
    }

    @Override
    public void updateUSerRole(Integer userRoleId, UserRole userRole) {
        Optional<UserRole> optionalUserRole = getUserRoleById(userRoleId);

        if(optionalUserRole.isPresent()) {
            UserRole existingUserRole = optionalUserRole.get();

            if(userRole.getRole() != null) {
                existingUserRole.setRole(userRole.getRole());
            }

            if(userRole.getUser() != null) {
                existingUserRole.setUser(userRole.getUser());
            }

            userRolesRepo.save(existingUserRole);
        }else {
            throw new EntityNotFoundException("UserRole with ID " + userRoleId + " not found");
        }
    }

    @Override
    public void deleteUserRole(Integer userRoleId) {
        Optional<UserRole> optionalUserRole = getUserRoleById(userRoleId);

        if(optionalUserRole.isPresent()) {
            userRolesRepo.deleteById(userRoleId);
        }
    }
}
