package com.milkit_shop.repository;

import com.milkit_shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
  Member findByEmail(String email);
}

