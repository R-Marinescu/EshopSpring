package com.eshop.services;

import com.eshop.models.User;
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
    public UserRole createUserRole(User user) {
        UserRole newUserRole = new UserRole();

        newUserRole.setUserId(user.getUserId());
        //Default user role
        newUserRole.setRoleId(2);

        return userRolesRepo.save(newUserRole);
    }

    @Override
    public void deleteUserRole(Integer userRoleId) {
        Optional<UserRole> optionalUserRole = getUserRoleById(userRoleId);

        if(optionalUserRole.isPresent()) {
            userRolesRepo.deleteById(userRoleId);
        }
    }
}
