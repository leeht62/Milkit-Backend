package com.milkit_shop.dto;

import com.milkit_shop.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
  private Long id;
  private String name;
  private String image;
  private String content;
  private int price;
  private int stock;


}
