package com.eshop.repositories;

import com.eshop.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends JpaRepository<UserRole, Integer> {
}
