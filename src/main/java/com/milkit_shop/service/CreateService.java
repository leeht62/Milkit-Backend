package com.milkit_shop.service;

import com.milkit_shop.constant.Category;
import com.milkit_shop.constant.SubCategory;
import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.Member;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateService {
  @Autowired ItemService itemService;
  @Autowired MemberService memberService;

  @PostConstruct
  public void initCreateMock() {

      Member AdminMember=createMember("관리자","admin@naver.com","123456789a!");
      Item item1 = createItem("부대찌개" , "/kit1.jpg","부대찌개입니다.",10000,5,Category.SOUP,SubCategory.JJIGAE);
      Item item2 = createItem("김치찌개" , "/kimchi.jpg","김치찌개입니다.",15000,5,Category.SOUP,SubCategory.JJIGAE);
      Item item3 = createItem("된장찌개" , "/dwon.jpg","된장찌개입니다.",9000,5,Category.SOUP,SubCategory.JJIGAE);
      Item item4 = createItem("초밥" , "/Sushi.jpg","초밥입니다.",10000,5,Category.ROAST,SubCategory.SUSHI);
      Item item5 = createItem("감자튀김" , "/french.jpg","감자튀김입니다.",3000,5,Category.ROAST,SubCategory.FRIED);
      Item item6 = createItem("고구마튀김" , "/french.jpg","고구마튀김입니다.",4000,5,Category.ROAST,SubCategory.FRIED);

      memberService.saveAdminMember(AdminMember);
      itemService.saveItem(item1);
      itemService.saveItem(item2);
      itemService.saveItem(item3);
      itemService.saveItem(item4);
      itemService.saveItem(item5);
      itemService.saveItem(item6);


  }

  private Item createItem(String name, String image, String content, int price, int stock, Category category, SubCategory subCategory) {
    Item item=new Item();
    item.setName(name);
    item.setImage(image);
    item.setContent(content);
    item.setPrice(price);
    item.setStockNumber(stock);
    item.setCategory(category);
    item.setSubcategory(subCategory);
    return item;
  }
  private Member createMember(String name, String email, String password){
    Member member=new Member();
    member.setName(name);
    member.setPassword(password);
    member.setEmail(email);
    return member;
  }


}
