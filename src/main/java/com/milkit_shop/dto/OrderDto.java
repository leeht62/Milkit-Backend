package com.milkit_shop.dto;

import com.milkit_shop.constant.Status;
import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.Order;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
  private Long id;
  private int count;
}
