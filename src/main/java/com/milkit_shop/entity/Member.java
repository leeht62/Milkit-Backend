package com.milkit_shop.entity;

import com.milkit_shop.constant.Role;
import com.milkit_shop.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
  @Id
  @Column(name="member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @Column(unique=true)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;


}