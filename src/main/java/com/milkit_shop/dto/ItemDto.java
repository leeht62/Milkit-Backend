package com.milkit_shop.dto;

import com.milkit_shop.constant.Category;
import com.milkit_shop.constant.ItemStatus;
import com.milkit_shop.constant.SubCategory;
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
  private String subcontent;

  private int price;
  private int stock;
  private Category category;
  private SubCategory subCategory;
  private ItemStatus itemStatus;



  public ItemDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.content = item.getContent();
    this.price = item.getPrice();
    this.image=item.getImage();
    this.stock=item.getStock();
    this.category=item.getCategory();
    this.subCategory=item.getSubcategory();
    this.itemStatus=item.getItemStatus();
    this.subcontent = item.getSubcontent();
  }
}
