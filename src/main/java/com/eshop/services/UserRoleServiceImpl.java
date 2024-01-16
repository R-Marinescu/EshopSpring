package com.eshop.services;

import com.eshop.models.UserRole;

import java.util.List;
import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService{
    @Override
    public Optional<UserRole> getUserRoleById(Integer userRoleId) {
        return Optional.empty();
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return null;
    }

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public UserRole updateUSerRole(Integer userRoleId, UserRole userRole) {
        return null;
    }

    @Override
    public void deleteUserRole(Integer userRoleId) {

    }
}
