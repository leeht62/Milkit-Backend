package com.milkit_shop.repository;

import com.milkit_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("select c from Cart c " +
            "where c.member.userCode = :userCode")
    Cart findByUserCode(@Param("userCode") String userCode);
}
