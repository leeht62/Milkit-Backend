package com.milkit_shop.dto;

import com.milkit_shop.constant.Recommend;
import com.milkit_shop.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDto {
  public OrderItemDto(OrderItem orderItem){
    this.itemId=orderItem.getItem().getId();
    this.name=orderItem.getItem().getName();
    this.count=orderItem.getCount();
    this.price=orderItem.getPrice();
    this.image=orderItem.getItem().getImage();
  }
  private Long itemId;

  private String name;

  private int count;

  private int price;

  private String image;



}
