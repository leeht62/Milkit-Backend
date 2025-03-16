package com.milkit_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@Data
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

  private String role;

  @CreationTimestamp
  private Timestamp createDate;



}