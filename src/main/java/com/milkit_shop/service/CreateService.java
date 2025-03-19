package com.milkit_shop.service;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.entity.Item;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;

@Service
@RequiredArgsConstructor
public class CreateService {
  @Autowired ItemService itemService;


  @PostConstruct
  public void initCreateMock() {

      Item item1 = createItem("부대찌개" , "/kit1.jpg","부대찌개입니다.",10000,5);
      Item item2 = createItem("김치찌개" , "/kimchi.jpg","김치찌개입니다.",15000,5);
      Item item3 = createItem("된장찌개" , "/dwon.jpg","된장찌개입니다.",9000,5);
      Item item4 = createItem("초밥" , "/Sushi.jpg","초밥입니다.",10000,5);
      Item item5 = createItem("감자튀김" , "/french.jpg","감자튀김입니다.",3000,5);

      itemService.saveItem(item1);
      itemService.saveItem(item2);
      itemService.saveItem(item3);
      itemService.saveItem(item4);
      itemService.saveItem(item5);


  }

  private Item createItem(String name,String image,String content, int price, int stock) {
    Item item=new Item();
    item.setName(name);
    item.setImage(image);
    item.setContent(content);
    item.setPrice(price);
    item.setStockNumber(stock);
    return item;
  }

}
