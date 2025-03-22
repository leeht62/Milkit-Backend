package com.milkit_shop.dto;

import com.milkit_shop.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemDto {
  public OrderItemDto(OrderItem orderItem){
    this.name=orderItem.getItem().getName();
    this.count=orderItem.getCount();
    this.price=orderItem.getPrice();
  }
  private String name;

  private int count;

  private int price;

}
