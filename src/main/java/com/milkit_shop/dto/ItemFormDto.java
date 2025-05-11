package com.milkit_shop.dto;

import com.milkit_shop.constant.Category;
import com.milkit_shop.constant.ItemStatus;
import com.milkit_shop.constant.SubCategory;
import com.milkit_shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemFormDto {

  private Long id;
  private String name;
  private Integer price;
  private String content;
  private String subcontent;
  private int stock;
  private ItemStatus itemStatus;
  private Category category;
  private SubCategory subCategory;

  private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
  private List<Long> itemImgIds = new ArrayList<>();
  private static ModelMapper modelMapper = new ModelMapper();

  public Item createItem(){
    return modelMapper.map(this, Item.class);
  }

  public static ItemFormDto of(Item item){
    return modelMapper.map(item, ItemFormDto.class);
  }
}