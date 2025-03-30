package com.milkit_shop.repository;

import com.milkit_shop.entity.Cart;
import com.milkit_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("select c from Cart c " +
            "where c.member.email = :email")
    Cart findByEmail(@Param("email") String email);
}
