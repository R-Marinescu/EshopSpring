package com.eshop.repositories;

import com.eshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findUserByUsername(String username);
}
