package com.milkit_shop.repository;

import com.milkit_shop.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
  @Query("select o from Order o " +
      "where o.member.email = :email"+
    "order by o.orderDate desc")
  List<Order> findOrders(@Param("email") String email);
}
