package com.milkit_shop.entity;

import com.milkit_shop.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "item_id")
  private Long id; //아이템 고유번호

  @Column(nullable=false)
  private String name; //상품 이름
  @Column(name="price",nullable=false)
  private int price; //상품 가격
  @Column(nullable = false)
  private int stockNumber;

  @Column(name = "image")
  private String image;

  private String content;




}
