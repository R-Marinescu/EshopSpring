package com.eshop.services;

import com.eshop.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleById(Integer roleId);
    List<Role> getAllRoles();
    Role createRole(Role role);
    Role updateRole(Integer roleId, Role role);
    void deleteRole(Integer roleId);
}
