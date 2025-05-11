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
    if (itemService.itemList().isEmpty()) {
      Item item1 = createItem("부대찌개", "/kit1.jpg", "다양한 햄과 소시지, 두부, 채소, 라면사리 등을 고추장 베이스 육수에 푹 끓여낸 얼큰하고 푸짐한 부대찌개입니다. 진한 국물맛과 풍성한 재료가 조화를 이루며, 한국식 퓨전 전골의 진수를 맛볼 수 있습니다.", 10000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item2 = createItem("김치찌개", "/kimchi.jpg", "잘 익은 김치를 베이스로 돼지고기, 두부, 대파 등을 넣어 얼큰하게 끓여낸 한국 전통 찌개입니다. 깊고 진한 국물 맛과 칼칼한 매운맛이 특징이며, 밥과 함께 먹으면 궁합이 뛰어납니다.", 15000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item3 = createItem("된장찌개", "/dwon.jpg", "한국 전통 된장을 베이스로 애호박, 두부, 버섯 등 다양한 채소를 넣고 끓여낸 구수한 된장찌개입니다. 담백하면서도 깊은 맛이 있으며, 한국인의 입맛에 친숙한 대표적인 집밥 메뉴입니다.", 9000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item4 = createItem("모둠초밥", "/Sushi.jpg", "신선한 생선이나 해산물을 얹은 초밥입니다. 쫀득한 밥과 감칠맛 나는 생선이 절묘하게 어우러지며, 입안 가득 고소하고 풍부한 풍미를 선사하는 일본 전통 요리입니다.", 10000, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item5 = createItem("감자튀김", "/french.jpg", "겉은 바삭하고 속은 포슬포슬한 감자를 황금빛으로 튀겨낸 감자튀김입니다. 짭조름한 간이 더해져 간식이나 사이드 메뉴로 누구에게나 사랑받는 대표적인 스낵입니다.", 3000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item6 = createItem("고구마튀김", "/french.jpg", "달콤한 고구마를 두툼하게 썰어 바삭하게 튀겨낸 고구마튀김입니다. 겉은 바삭하고 속은 촉촉하며, 특유의 은은한 단맛이 입맛을 돋웁니다.", 4000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item7 = createItem("해산물토마토파스타", "/seatomatopasta.jpg", "새우, 오징어 등 다양한 해산물이 듬뿍 들어간 토마토 베이스 파스타입니다. 신선한 해산물의 풍미와 새콤달콤한 토마토 소스가 어우러져 입안 가득 바다의 향이 퍼집니다.", 13000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item8 = createItem("토마토해장파스타", "/tomatohaejangPasta.jpg", "매콤한 토마토 소스로 해장 효과를 더한 이색적인 파스타입니다. 얼큰하고 진한 소스가 속을 확 풀어주는 느낌을 주며, 숙취 해소에 좋은 이색 메뉴로 인기를 끌고 있습니다.", 12000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item9 = createItem("크림나베", "/creamNabe.jpg", "부드러운 크림 소스에 다양한 해산물과 채소를 넣어 만든 일본식 크림 나베입니다. 고소한 크림 풍미와 담백한 육수가 어우러져 부드럽고 진한 맛을 자랑합니다.", 14000, 5, Category.JFOOD, SubCategory.NABE, ItemStatus.NEW);
      Item item10 = createItem("알탕", "/eggSoup.jpg", "명란과 각종 해산물을 매콤한 육수에 푹 끓여낸 한국식 해물탕입니다. 얼큰한 국물이 속을 시원하게 풀어주며, 술안주 또는 밥반찬으로도 손색이 없습니다.", 11000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item11 = createItem("소세지야채볶음", "/Soya.jpg", "탱글탱글한 소시지와 다양한 채소를 간장 베이스 양념에 볶아낸 요리입니다. 짭짤하면서도 달콤한 맛이 조화를 이루며, 남녀노소 누구나 즐길 수 있는 밥반찬입니다.", 9500, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item12 = createItem("베이컨토마토파스타", "/bacontomatoPasta.jpg", "짭조름한 베이컨과 새콤달콤한 토마토 소스가 어우러진 파스타입니다. 풍부한 풍미와 간단하지만 감칠맛 나는 조합으로 누구나 만족하는 메뉴입니다.", 10500, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item13 = createItem("멜론 샤베트", "/Melonsorbet.jpg", "시원하고 달콤한 멜론 맛이 살아있는 샤베트입니다. 부드럽고 상큼한 과일 풍미가 더위 속에 청량감을 선사하며, 디저트로 인기가 많습니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item14 = createItem("레몬 샤베트", "/Lemonsorbet.jpg", "톡 쏘는 상큼한 레몬의 맛이 살아있는 샤베트입니다. 입안이 상쾌해지는 청량감과 시원함으로 식사 후 입가심으로 제격입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item15 = createItem("코코넛 샤베트", "/Cocosorbet.jpg", "고소한 코코넛 풍미가 가득한 샤베트입니다. 진하고 부드러운 맛이 특징이며, 이국적인 디저트를 찾는 분들께 잘 어울리는 메뉴입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item16 = createItem("요구르트 샤베트", "/Yogurtsorbet.jpg", "상큼하고 부드러운 요구르트의 풍미가 살아있는 샤베트입니다. 새콤달콤한 맛이 입안을 깔끔하게 정리해주며, 부담 없는 디저트로 좋습니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item17 = createItem("한라봉 샤베트", "/Hallasorbet.jpg", "제주 한라봉의 상큼한 맛을 그대로 담은 샤베트입니다. 진한 과즙의 향이 입안 가득 퍼지며, 남녀노소 누구나 즐기기 좋은 달콤한 디저트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item18 = createItem("샤인머스켓 샤베트", "/Shinesorbet.jpg", "고급 포도 품종인 샤인머스켓의 달콤하고 풍부한 향을 담은 샤베트입니다. 깔끔하면서도 화사한 맛이 특징이며, 시원하고 고급스러운 디저트를 찾는 분들께 추천드립니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item19 = createItem("타코야끼", "/takoyaki.jpg", "쫄깃한 문어 조각을 넣어 동그랗게 구워낸 일본식 간식입니다. 겉은 바삭하고 속은 부드러운 반죽에 달콤짭조름한 소스, 마요네즈, 가쓰오부시가 올라가 풍성한 식감을 자랑합니다. 간단한 간식이나 맥주 안주로도 인기 있습니다.", 5000, 10, Category.ANJU, SubCategory.FRIED, ItemStatus.NEW);
      Item item20 = createItem("연어 사시미", "/image20.jpg", "신선한 노르웨이산 연어를 도톰하게 썰어낸 사시미입니다. 기름기가 적당히 올라와 입안에서 사르르 녹으며, 고소하면서도 담백한 풍미가 일품입니다. 간장과 고추냉이와 함께 곁들이면 깊은 감칠맛을 느낄 수 있습니다.", 5000, 10, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item21 = createItem("참치 사시미", "/image21.jpg", "신선한 참치를 도톰하게 썰어낸 사시미입니다. 부드럽고 진한 풍미가 입안에서 사르르 녹으며, 고소하면서도 깊은 맛이 일품입니다. 간장과 고추냉이를 곁들이면 특유의 감칠맛이 더욱 살아납니다.", 12000, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item22 = createItem("일식 도시락", "/image22.jpg", "다양한 반찬이 정갈하게 담긴 정통 일식 도시락입니다. 구운 연어, 계란말이, 간장으로 조리한 버섯, 제철 채소절임 등으로 구성되어 있으며, 보기에도 먹기에도 만족스러운 프리미엄 도시락입니다.", 13000, 5, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item23 = createItem("오뎅 나베", "/image23.jpg", "다양한 모양과 맛의 오뎅을 진한 다시 육수에 푹 끓여낸 일본식 전골입니다. 무, 유부주머니, 곤약 등 속재료가 풍부하게 들어가며, 추운 날씨에 어울리는 따뜻한 국물요리입니다.", 11000, 5, Category.JFOOD, SubCategory.NABE, ItemStatus.NEW);
      Item item24 = createItem("참치 마요 주먹밥", "/image24.jpg", "부드럽고 고소한 참치마요네즈가 가득 들어간 일본식 주먹밥입니다. 한입 크기로 먹기 좋아 간편식으로 제격이며, 김과 밥의 조화로 담백하면서도 중독성 있는 맛을 자랑합니다.", 3500, 10, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item25 = createItem("가쓰오 우동", "/image25.jpg", "가쓰오부시(가다랑어포)로 우려낸 깊고 진한 국물에 쫄깃한 우동 면을 더한 정통 일본식 우동입니다. 깔끔한 국물맛이 특징이며, 대파와 튀김 부스러기를 고명으로 올려 감칠맛을 더했습니다.", 9000, 5, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item26 = createItem("닭가라아게", "/image26.jpg", "겉은 바삭하고 속은 촉촉한 일본식 닭튀김입니다. 생강과 간장으로 재운 닭고기를 튀겨내어 고소하고 짭조름한 맛이 조화를 이루며, 맥주 안주나 간편한 한 끼로도 인기가 좋습니다.", 8000, 10, Category.JFOOD, SubCategory.JFRIED, ItemStatus.NEW);
      Item item27 = createItem("가츠동", "/image27.jpg", "바삭하게 튀긴 돈까스를 달콤한 간장 베이스의 소스와 부드러운 계란으로 감싸 따뜻한 밥 위에 얹은 일본식 덮밥입니다. 고기의 풍미와 촉촉한 계란, 감칠맛 가득한 양념이 어우러져 누구나 좋아할 수 있는 대표적인 일식 메뉴입니다.", 10000, 5, Category.JFOOD, SubCategory.JFRIED, ItemStatus.NEW);
      Item item28 = createItem("에비텐", "/image28.jpg", "신선한 대하를 바삭한 튀김옷에 감싸 고온에서 튀겨낸 일본식 새우튀김입니다. 탱글탱글한 새우살과 바삭한 식감이 조화를 이루며, 튀김소스나 간장에 살짝 찍어 먹으면 더욱 맛이 살아납니다.", 6000, 5, Category.JFOOD, SubCategory.JFRIED, ItemStatus.NEW);
      Item item29 = createItem("사바 간장구이", "/image29.jpg", "기름기 좋은 고등어를 달짝지근한 간장소스에 졸여내듯 구워낸 요리입니다. 살이 부드럽고 진한 감칠맛이 배어 있어 밥반찬으로도 손색이 없습니다. 레몬 한 조각과 함께 곁들이면 더욱 담백하게 즐길 수 있습니다.", 9500, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item30 = createItem("츠케모노", "/image30.jpg", "무, 오이, 가지 등 다양한 채소를 소금과 식초, 다시 등에 절여 만든 일본 전통 반찬입니다. 아삭하고 새콤한 맛이 입맛을 돋우며, 느끼한 음식과 함께 먹으면 깔끔한 밸런스를 유지해줍니다.", 3000, 10, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item31 = createItem("연어 초밥", "/image31.jpg", "신선한 연어를 두툼하게 썰어 쫀득한 초밥 위에 얹은 메뉴입니다. 기름진 연어의 고소한 풍미가 입안 가득 퍼지며, 부드러운 밥과의 조화가 일품입니다.", 11000, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item32 = createItem("참치 초밥", "/image32.jpg", "부드럽고 담백한 참치살을 사용한 대표적인 초밥입니다. 적당한 두께로 썰어내어 밥과 함께 즐기면 담백함과 고소함이 입안에 맴도는 정통 스시입니다.", 11500, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item33 = createItem("장어 초밥", "/image33.jpg", "달콤한 간장 양념에 구운 부드러운 장어를 올린 초밥입니다. 입안에서 녹는 듯한 장어와 달짝지근한 소스가 조화를 이루어 깊은 풍미를 전합니다.", 13000, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item34 = createItem("새우 초밥", "/image34.jpg", "탱글한 식감의 새우를 살짝 익혀 밥 위에 올린 새우 초밥입니다. 단맛이 도는 새우의 풍미와 쫀득한 밥이 어우러져 누구나 부담 없이 즐길 수 있습니다.", 10500, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item35 = createItem("계란 초밥", "/image35.jpg", "달콤하게 조리된 일본식 계란말이를 밥 위에 얹은 계란 초밥입니다. 부드러운 계란의 식감과 달짝지근한 맛이 특징으로, 어린이나 초밥 입문자에게 인기가 많습니다.", 9500, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
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
      itemService.saveItem(item19);
      itemService.saveItem(item20);
      itemService.saveItem(item21);
      itemService.saveItem(item22);
      itemService.saveItem(item23);
      itemService.saveItem(item24);
      itemService.saveItem(item25);
      itemService.saveItem(item26);
      itemService.saveItem(item27);
      itemService.saveItem(item28);
      itemService.saveItem(item29);
      itemService.saveItem(item30);
      itemService.saveItem(item31);
      itemService.saveItem(item32);
      itemService.saveItem(item33);
      itemService.saveItem(item34);
      itemService.saveItem(item35);
    }
    memberService.saveAdminMember(AdminMember);
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
