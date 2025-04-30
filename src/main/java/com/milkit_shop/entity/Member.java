package com.milkit_shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkit_shop.constant.ItemStatus;
import com.milkit_shop.constant.LoginType;
import com.milkit_shop.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

  @Column(unique=true)
  private String userCode;

  private String password;

  private String name;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  private LoginType loginType;

  @Enumerated(EnumType.STRING)
  private ItemStatus login;

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Board> boards = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Comment> comments = new ArrayList<>();

  @JsonIgnore
  @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Cart cart;

  @JsonIgnore
  @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private Order order;

  @JsonIgnore
  @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Address> addresses = new ArrayList<>();

}