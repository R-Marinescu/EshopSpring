package com.eshop.repositories;

import com.eshop.models.Order;
import com.eshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT o.user.userId FROM Order o WHERE o.orderId = :orderId")
    Integer findUserIdByOrderId(Integer orderId);
}
