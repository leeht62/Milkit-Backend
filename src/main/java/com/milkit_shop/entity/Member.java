package com.milkit_shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkit_shop.constant.LoginType;
import com.milkit_shop.constant.Role;
import com.milkit_shop.constant.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "아이디는 필수입니다.")
  @Column(unique=true)
  private String userCode;

  @NotBlank(message = "비밀번호는 필수입니다.")
  private String password;

  private String name;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  private LoginType loginType;

  @Column
  @Enumerated(EnumType.STRING)
  private User user;

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Board> boards = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Comment> comments = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Cart> carts = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Order> orders = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Address> addresses = new ArrayList<>();

  public void checkOrder(int orderCount) {
    this.user = orderCount >= 5 ? User.VIP : User.GENERAL ;
  }

}