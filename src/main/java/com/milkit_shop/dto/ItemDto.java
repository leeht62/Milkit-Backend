package com.milkit_shop.dto;

import com.milkit_shop.constant.Category;
import com.milkit_shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemDto {
  private Long id;
  private String name;
  private String image;
  private String content;
  private int price;
  private int stock;
  private Category category;


  public ItemDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.content = item.getContent();
    this.price = item.getPrice();
    this.image=item.getImage();
    this.stock=item.getStockNumber();
    this.category=item.getCategory();
  }
}
