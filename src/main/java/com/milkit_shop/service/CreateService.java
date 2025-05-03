package com.milkit_shop.service;

import com.milkit_shop.constant.Category;
import com.milkit_shop.constant.ItemStatus;
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
    Member AdminMember = createMember("관리자","admin","123456789a!");
    Item item1 = createItem("부대찌개" , "/kit1.jpg","부대찌개입니다.",10000,5,Category.SOUP,SubCategory.JJIGAE,ItemStatus.NEW);
    Item item2 = createItem("김치찌개" , "/kimchi.jpg","김치찌개입니다.",15000,5,Category.SOUP,SubCategory.JJIGAE,ItemStatus.NEW);
    Item item3 = createItem("된장찌개" , "/dwon.jpg","된장찌개입니다.",9000,5,Category.SOUP,SubCategory.JJIGAE,ItemStatus.NEW);
    Item item4 = createItem("초밥" , "/Sushi.jpg","초밥입니다.",10000,5,Category.ROAST,SubCategory.SUSHI,ItemStatus.NEW);
    Item item5 = createItem("감자튀김" , "/french.jpg","감자튀김입니다.",3000,5,Category.ROAST,SubCategory.FRIED,ItemStatus.NEW);
    Item item6 = createItem("고구마튀김" , "/french.jpg","고구마튀김입니다.",4000,5,Category.ROAST,SubCategory.FRIED,ItemStatus.NEW);
    Item item7 = createItem("해산물토마토파스타", "/seatomatopasta.jpg", "해산물이 들어간 토마토 파스타입니다.", 13000, 5, Category.PASTA, SubCategory.Noodle,ItemStatus.NEW);
    Item item8 = createItem("토마토해장파스타", "/tomatohaejangPasta.jpg", "해장용 토마토 파스타입니다.", 12000, 5, Category.PASTA, SubCategory.Noodle,ItemStatus.NEW);
    Item item9 = createItem("크림나베", "/creamNabe.jpg", "크림이 들어간 일본식 나베입니다.", 14000, 5, Category.SOUP, SubCategory.Noodle,ItemStatus.NEW);
    Item item10 = createItem("알탕", "/eggSoup.jpg", "얼큰한 알탕입니다.", 11000, 5, Category.SOUP, SubCategory.JJIGAE,ItemStatus.NEW);
    Item item11 = createItem("소세지야채볶음", "/Soya.jpg", "소세지와 야채를 함께 볶은 요리입니다.", 9500, 5, Category.ROAST, SubCategory.BOKKEUM,ItemStatus.NEW);
    Item item12 = createItem("베이컨토마토파스타", "/bacontomatoPasta.jpg", "베이컨과 토마토를 볶아 만든 요리입니다.", 10500, 5, Category.PASTA, SubCategory.Noodle,ItemStatus.NEW);
    Item item13 = createItem("멜론 샤베트", "/Melonsorbet.jpg", "달콤한 멜론 샤베트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET,ItemStatus.NEW);
    Item item14 = createItem("레몬 샤베트", "/Lemonsorbet.jpg", "상큼한 레몬 샤베트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET,ItemStatus.NEW);
    Item item15 = createItem("코코넛 샤베트", "/Cocosorbet.jpg", "고소한 코코넛 샤베트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET,ItemStatus.NEW);
    Item item16 = createItem("요구르트 샤베트", "/Yogurtsorbet.jpg", "상큼한 요구르트 샤베트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET,ItemStatus.NEW);
    Item item17 = createItem("한라봉 샤베트", "/Hallasorbet.jpg", "제주 한라봉 샤베트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET,ItemStatus.NEW);
    Item item18 = createItem("샤인머스켓 샤베트", "/Shinesorbet.jpg", "달콤한 샤인머스켓 샤베트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET,ItemStatus.NEW);
    Item item19 = createItem("타코야끼", "/takoyaki.jpg", "일본식 문어볼 요리입니다.", 5000, 10, Category.ANJU, SubCategory.FRIED,ItemStatus.NEW);
    memberService.saveAdminMember(AdminMember);
    itemService.saveItem(item1);
    itemService.saveItem(item2);
    itemService.saveItem(item3);
    itemService.saveItem(item4);
    itemService.saveItem(item5);
    itemService.saveItem(item6);
    itemService.saveItem(item7);
    itemService.saveItem(item8);
    itemService.saveItem(item9);
    itemService.saveItem(item10);
    itemService.saveItem(item11);
    itemService.saveItem(item12);
    itemService.saveItem(item13);
    itemService.saveItem(item14);
    itemService.saveItem(item15);
    itemService.saveItem(item16);
    itemService.saveItem(item17);
    itemService.saveItem(item18);
    itemService.saveItem(item19);

  }

  private Item createItem(String name, String image, String content, int price, int stock, Category category, SubCategory subCategory, ItemStatus itemStatus) {
    Item item=new Item();
    item.setName(name);
    item.setImage(image);
    item.setContent(content);
    item.setPrice(price);
    item.setStock(stock);
    item.setCategory(category);
    item.setSubcategory(subCategory);
    item.setItemStatus(itemStatus);
    return item;
  }
  private Member createMember(String name, String userCode, String password){
    Member member=new Member();
    member.setName(name);
    member.setPassword(password);
    member.setUserCode(userCode);
    return member;
  }


}
