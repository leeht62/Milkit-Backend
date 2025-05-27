package com.milkit_shop.entity;

import com.milkit_shop.constant.Category;
import com.milkit_shop.constant.ItemStatus;
import com.milkit_shop.constant.SubCategory;
import com.milkit_shop.dto.ItemFormDto;
import com.milkit_shop.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

  @Column(name="name",nullable=false)
  private String name; //상품 이름
  @Column(name="price",nullable=false)
  private int price; //상품 가격
  @Column(name="stock",nullable = false)
  private int stock;

  @Column(name = "image")
  private String image;

  private String content;

  private String subcontent;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(length = 50)
  @Enumerated(EnumType.STRING)
  private SubCategory subcategory;

  @Enumerated(EnumType.STRING)
  private ItemStatus itemStatus;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<ItemImg> itemImgs = new ArrayList<>();

  public void updateItemStatus() {
    if (this.itemStatus == ItemStatus.NEW) {
      this.itemStatus = ItemStatus.BEST;
      this.price += 1000;
    }
  }

  public void addStock(int count) {
    stock += count;
  }

  public void removeStock(int count) {
    if (stock - count < 0) {
      throw new OutOfStockException("상품 재고 부족");
    }
    stock -= count;
    updateItemStatus();
  }
  public void ifDiscount(){
    if(this.itemStatus==ItemStatus.DISCOUNT){
      this.price = (int)(this.price * 0.7);
    }
  }

  public void updateItem(ItemFormDto itemFormDto){
    this.name = itemFormDto.getName();
    this.price = itemFormDto.getPrice();
    this.stock=itemFormDto.getStock();
    this.content=itemFormDto.getContent();
    this.itemStatus=itemFormDto.getItemStatus();
    this.category=itemFormDto.getCategory();
    this.subcategory=itemFormDto.getSubCategory();
    ifDiscount();
  }


}