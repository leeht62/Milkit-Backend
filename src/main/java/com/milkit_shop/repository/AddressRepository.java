package com.milkit_shop.repository;

import com.milkit_shop.entity.Address;
import com.milkit_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a " +
            "where a.member.email = :email")
    List<Address> findAllByEmail(@Param("email") String email);
}
