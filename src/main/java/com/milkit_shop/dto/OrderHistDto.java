package com.milkit_shop.dto;

import com.milkit_shop.constant.Status;
import com.milkit_shop.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderHistDto {
  public OrderHistDto(Order order){
    this.orderId=order.getId();
    this.orderDate=order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    this.status=order.getStatus();

  }
  private Long orderId;
  private String orderDate;
  private Status status;

  private List<OrderItemDto> orderItemDtoList=new ArrayList<>();
  public void addOrderItemDto(OrderItemDto orderItemDto){
    orderItemDtoList.add(orderItemDto);
  }
}
