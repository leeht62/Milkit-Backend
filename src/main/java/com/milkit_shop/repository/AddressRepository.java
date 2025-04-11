package com.milkit_shop.repository;

import com.milkit_shop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a " +
            "WHERE a.member_id=member_id")
    List<Address> findAllByMemberId(@Param("member_id") int memberId);
}
