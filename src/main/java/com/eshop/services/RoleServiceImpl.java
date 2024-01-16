package com.eshop.services;

import com.eshop.models.Role;

import java.util.List;
import java.util.Optional;

public class RoleServiceImpl implements RoleService{
    @Override
    public Optional<Role> getRoleById(Integer roleId) {
        return Optional.empty();
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public Role updateRole(Integer roleId, Role role) {
        return null;
    }

    @Override
    public void deleteRole(Integer roleId) {

    }
}
