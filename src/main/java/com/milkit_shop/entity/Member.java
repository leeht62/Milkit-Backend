package com.milkit_shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  private String name;

  @Column(unique=true)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Board> boards = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "member")
  private List<Comment> comments = new ArrayList<>();
}