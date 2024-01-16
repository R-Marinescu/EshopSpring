package com.eshop.services;

import com.eshop.models.Role;
import com.eshop.repositories.RoleRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Optional<Role> getRoleById(Integer roleId) {
        return roleRepo.findById(roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void updateRole(Integer roleId, Role role) {
        Optional<Role> optionalRole = getRoleById(roleId);

        if(optionalRole.isPresent()) {
            Role existingRole = optionalRole.get();

            if(role.getRoleName() != null) {
                existingRole.setRoleName(role.getRoleName());
            }

            roleRepo.save(existingRole);
        }else {
            throw new EntityNotFoundException("Role with ID " + roleId + " not found");
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        Optional<Role> optionalRole = getRoleById(roleId);

        if(optionalRole.isPresent()) {
            roleRepo.deleteById(roleId);
        }
    }
}
