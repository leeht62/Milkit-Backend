package com.milkit_shop.entity;

import com.milkit_shop.constant.Category;
import com.milkit_shop.constant.ItemStatus;
import com.milkit_shop.constant.SubCategory;
import com.milkit_shop.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
  @Column(nullable = false)
  private int stockNumber;

  @Column(name = "image")
  private String image;

  private String content;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Enumerated(EnumType.STRING)
  private SubCategory subcategory;

  @Enumerated(EnumType.STRING)
  private ItemStatus itemStatus;

  public void updateItemStatus() {
    this.itemStatus = this.stockNumber <= 2 ? ItemStatus.BEST : ItemStatus.NEW;
  }

  public void addStock(int count) {
    stockNumber += count;
  }

  public void removeStock(int count) {
    if (stockNumber - count < 0) {
      throw new OutOfStockException("상품 재고 부족");
    }
    stockNumber -= count;
    updateItemStatus();
  }
}
