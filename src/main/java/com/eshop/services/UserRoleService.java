package com.eshop.services;

import com.eshop.models.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> getUserRoleById(Integer userRoleId);
    List<UserRole> getAllUserRoles();
    UserRole createUserRole(UserRole userRole);
    void updateUSerRole(Integer userRoleId, UserRole userRole);
    void deleteUserRole(Integer userRoleId);
}
