package com.eshop.repositories;

import com.eshop.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r JOIN UserRole ur ON r.roleId = ur.role.roleId WHERE ur.user.userId = :userId")
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}
