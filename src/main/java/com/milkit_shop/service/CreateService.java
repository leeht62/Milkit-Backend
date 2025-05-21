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
      Item item1 = createItem("부대찌개", "/kit1.jpg", "부대찌개 입니다.", "다양한 햄과 소시지, 두부, 채소, 라면사리 등을 고추장 베이스 육수에 푹 끓여낸 얼큰하고 푸짐한 부대찌개입니다. 진한 국물맛과 풍성한 재료가 조화를 이루며, 한국식 퓨전 전골의 진수를 맛볼 수 있습니다.", 10000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item2 = createItem("김치찌개", "/kimchi.jpg", "김치찌개 입니다.", "잘 익은 김치를 베이스로 돼지고기, 두부, 대파 등을 넣어 얼큰하게 끓여낸 한국 전통 찌개입니다. 깊고 진한 국물 맛과 칼칼한 매운맛이 특징이며, 밥과 함께 먹으면 궁합이 뛰어납니다.", 15000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item3 = createItem("된장찌개", "/dwon.jpg", "된장찌개 입니다.", "한국 전통 된장을 베이스로 애호박, 두부, 버섯 등 다양한 채소를 넣고 끓여낸 구수한 된장찌개입니다. 담백하면서도 깊은 맛이 있으며, 한국인의 입맛에 친숙한 대표적인 집밥 메뉴입니다.", 9000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item4 = createItem("모둠초밥", "/Sushi.jpg", "모둠초밥 입니다.", "신선한 생선이나 해산물을 얹은 초밥입니다. 쫀득한 밥과 감칠맛 나는 생선이 절묘하게 어우러지며, 입안 가득 고소하고 풍부한 풍미를 선사하는 일본 전통 요리입니다.", 10000, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item5 = createItem("감자튀김", "/french.jpg", "감자튀김 입니다.", "겉은 바삭하고 속은 포슬포슬한 감자를 황금빛으로 튀겨낸 감자튀김입니다. 짭조름한 간이 더해져 간식이나 사이드 메뉴로 누구에게나 사랑받는 대표적인 스낵입니다.", 3000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item6 = createItem("고구마튀김", "/sweetpotato.jpg", "고구마튀김 입니다.", "달콤한 고구마를 두툼하게 썰어 바삭하게 튀겨낸 고구마튀김입니다. 겉은 바삭하고 속은 촉촉하며, 특유의 은은한 단맛이 입맛을 돋웁니다.", 4000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item7 = createItem("해산물토마토파스타", "/seatomatopasta.jpg", "해산물토마토파스타 입니다.", "새우, 오징어 등 다양한 해산물이 듬뿍 들어간 토마토 베이스 파스타입니다. 신선한 해산물의 풍미와 새콤달콤한 토마토 소스가 어우러져 입안 가득 바다의 향이 퍼집니다.", 13000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item8 = createItem("토마토해장파스타", "/tomatohaejangPasta.jpg", "토마토해장파스타 입니다.", "매콤한 토마토 소스로 해장 효과를 더한 이색적인 파스타입니다. 얼큰하고 진한 소스가 속을 확 풀어주는 느낌을 주며, 숙취 해소에 좋은 이색 메뉴로 인기를 끌고 있습니다.", 12000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item9 = createItem("크림나베", "/creamNabe.jpg", "크림나베 입니다.", "부드러운 크림 소스에 다양한 해산물과 채소를 넣어 만든 일본식 크림 나베입니다. 고소한 크림 풍미와 담백한 육수가 어우러져 부드럽고 진한 맛을 자랑합니다.", 14000, 5, Category.JFOOD, SubCategory.NABE, ItemStatus.NEW);
      Item item10 = createItem("알탕", "/eggSoup.jpg", "알탕 입니다.", "명란과 각종 해산물을 매콤한 육수에 푹 끓여낸 한국식 해물탕입니다. 얼큰한 국물이 속을 시원하게 풀어주며, 술안주 또는 밥반찬으로도 손색이 없습니다.", 11000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item11 = createItem("소세지야채볶음", "/Soya.jpg", "소세지야채볶음 입니다.", "탱글탱글한 소시지와 다양한 채소를 간장 베이스 양념에 볶아낸 요리입니다. 짭짤하면서도 달콤한 맛이 조화를 이루며, 남녀노소 누구나 즐길 수 있는 밥반찬입니다.", 9500, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item12 = createItem("베이컨토마토파스타", "/bacontomatoPasta.jpg", "베이컨토마토파스타 입니다.", "짭조름한 베이컨과 새콤달콤한 토마토 소스가 어우러진 파스타입니다. 풍부한 풍미와 간단하지만 감칠맛 나는 조합으로 누구나 만족하는 메뉴입니다.", 10500, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item13 = createItem("멜론 샤베트", "/Melonsorbet.jpg", "멜론 샤베트 입니다.", "시원하고 달콤한 멜론 맛이 살아있는 샤베트입니다. 부드럽고 상큼한 과일 풍미가 더위 속에 청량감을 선사하며, 디저트로 인기가 많습니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item14 = createItem("레몬 샤베트", "/Lemonsorbet.jpg", "레몬 샤베트 입니다.", "톡 쏘는 상큼한 레몬의 맛이 살아있는 샤베트입니다. 입안이 상쾌해지는 청량감과 시원함으로 식사 후 입가심으로 제격입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item15 = createItem("코코넛 샤베트", "/Cocosorbet.jpg", "코코넛 샤베트 입니다.", "고소한 코코넛 풍미가 가득한 샤베트입니다. 진하고 부드러운 맛이 특징이며, 이국적인 디저트를 찾는 분들께 잘 어울리는 메뉴입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item16 = createItem("요구르트 샤베트", "/Yogurtsorbet.jpg", "요구르트 샤베트 입니다.", "상큼하고 부드러운 요구르트의 풍미가 살아있는 샤베트입니다. 새콤달콤한 맛이 입안을 깔끔하게 정리해주며, 부담 없는 디저트로 좋습니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item17 = createItem("한라봉 샤베트", "/Hallasorbet.jpg", "한라봉 샤베트 입니다.", "제주 한라봉의 상큼한 맛을 그대로 담은 샤베트입니다. 진한 과즙의 향이 입안 가득 퍼지며, 남녀노소 누구나 즐기기 좋은 달콤한 디저트입니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item18 = createItem("샤인머스켓 샤베트", "/Shinesorbet.jpg", "샤인머스켓 샤베트 입니다.", "고급 포도 품종인 샤인머스켓의 달콤하고 풍부한 향을 담은 샤베트입니다. 깔끔하면서도 화사한 맛이 특징이며, 시원하고 고급스러운 디저트를 찾는 분들께 추천드립니다.", 3500, 10, Category.ANJU, SubCategory.SORBET, ItemStatus.NEW);
      Item item19 = createItem("타코야끼", "/takoyaki.jpg", "타코야끼 입니다.", "쫄깃한 문어 조각을 넣어 동그랗게 구워낸 일본식 간식입니다. 겉은 바삭하고 속은 부드러운 반죽에 달콤짭조름한 소스, 마요네즈, 가쓰오부시가 올라가 풍성한 식감을 자랑합니다. 간단한 간식이나 맥주 안주로도 인기 있습니다.", 5000, 10, Category.ANJU, SubCategory.FRIED, ItemStatus.NEW);
      Item item20 = createItem("연어 사시미", "/image20.jpg", "연어 사시미 입니다.", "신선한 노르웨이산 연어를 도톰하게 썰어낸 사시미입니다. 기름기가 적당히 올라와 입안에서 사르르 녹으며, 고소하면서도 담백한 풍미가 일품입니다. 간장과 고추냉이와 함께 곁들이면 깊은 감칠맛을 느낄 수 있습니다.", 5000, 10, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item21 = createItem("참치 사시미", "/image21.jpg", "참치 사시미 입니다.", "신선한 참치를 도톰하게 썰어낸 사시미입니다. 부드럽고 진한 풍미가 입안에서 사르르 녹으며, 고소하면서도 깊은 맛이 일품입니다. 간장과 고추냉이를 곁들이면 특유의 감칠맛이 더욱 살아납니다.", 12000, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item22 = createItem("일식 도시락", "/image22.jpg", "일식 도시락 입니다.", "다양한 반찬이 정갈하게 담긴 정통 일식 도시락입니다. 구운 연어, 계란말이, 간장으로 조리한 버섯, 제철 채소절임 등으로 구성되어 있으며, 보기에도 먹기에도 만족스러운 프리미엄 도시락입니다.", 13000, 5, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item23 = createItem("오뎅 나베", "/image23.jpg", "오뎅 나베 입니다.", "다양한 모양과 맛의 오뎅을 진한 다시 육수에 푹 끓여낸 일본식 전골입니다. 무, 유부주머니, 곤약 등 속재료가 풍부하게 들어가며, 추운 날씨에 어울리는 따뜻한 국물요리입니다.", 11000, 5, Category.JFOOD, SubCategory.NABE, ItemStatus.NEW);
      Item item24 = createItem("참치 마요 주먹밥", "/image24.jpg", "참치 마요 주먹밥 입니다.", "부드럽고 고소한 참치마요네즈가 가득 들어간 일본식 주먹밥입니다. 한입 크기로 먹기 좋아 간편식으로 제격이며, 김과 밥의 조화로 담백하면서도 중독성 있는 맛을 자랑합니다.", 3500, 10, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item25 = createItem("가쓰오 우동", "/image25.jpg", "가쓰오 우동 입니다.", "가쓰오부시(가다랑어포)로 우려낸 깊고 진한 국물에 쫄깃한 우동 면을 더한 정통 일본식 우동입니다. 깔끔한 국물맛이 특징이며, 대파와 튀김 부스러기를 고명으로 올려 감칠맛을 더했습니다.", 9000, 5, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item26 = createItem("닭가라아게", "/image26.jpg", "닭가라아게 입니다.", "겉은 바삭하고 속은 촉촉한 일본식 닭튀김입니다. 생강과 간장으로 재운 닭고기를 튀겨내어 고소하고 짭조름한 맛이 조화를 이루며, 맥주 안주나 간편한 한 끼로도 인기가 좋습니다.", 8000, 10, Category.JFOOD, SubCategory.JFRIED, ItemStatus.NEW);
      Item item27 = createItem("가츠동", "/image27.jpg", "가츠동 입니다.", "바삭하게 튀긴 돈까스를 달콤한 간장 베이스의 소스와 부드러운 계란으로 감싸 따뜻한 밥 위에 얹은 일본식 덮밥입니다. 고기의 풍미와 촉촉한 계란, 감칠맛 가득한 양념이 어우러져 누구나 좋아할 수 있는 대표적인 일식 메뉴입니다.", 10000, 5, Category.JFOOD, SubCategory.JFRIED, ItemStatus.NEW);
      Item item28 = createItem("에비텐", "/image28.jpg", "에비텐 입니다.", "신선한 대하를 바삭한 튀김옷에 감싸 고온에서 튀겨낸 일본식 새우튀김입니다. 탱글탱글한 새우살과 바삭한 식감이 조화를 이루며, 튀김소스나 간장에 살짝 찍어 먹으면 더욱 맛이 살아납니다.", 6000, 5, Category.JFOOD, SubCategory.JFRIED, ItemStatus.NEW);
      Item item29 = createItem("사바 간장구이", "/image29.jpg", "사바 간장구이 입니다.", "기름기 좋은 고등어를 달짝지근한 간장소스에 졸여내듯 구워낸 요리입니다. 살이 부드럽고 진한 감칠맛이 배어 있어 밥반찬으로도 손색이 없습니다. 레몬 한 조각과 함께 곁들이면 더욱 담백하게 즐길 수 있습니다.", 9500, 5, Category.JFOOD, SubCategory.FISH, ItemStatus.NEW);
      Item item30 = createItem("츠케모노", "/image30.jpg", "츠케모노 입니다.", "무, 오이, 가지 등 다양한 채소를 소금과 식초, 다시 등에 절여 만든 일본 전통 반찬입니다. 아삭하고 새콤한 맛이 입맛을 돋우며, 느끼한 음식과 함께 먹으면 깔끔한 밸런스를 유지해줍니다.", 3000, 10, Category.JFOOD, SubCategory.REST, ItemStatus.NEW);
      Item item31 = createItem("연어 초밥", "/image31.jpg", "연어 초밥 입니다.", "신선한 연어를 두툼하게 썰어 쫀득한 초밥 위에 얹은 메뉴입니다. 기름진 연어의 고소한 풍미가 입안 가득 퍼지며, 부드러운 밥과의 조화가 일품입니다.", 11000, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item32 = createItem("참치 초밥", "/image32.jpg", "참치 초밥 입니다.", "부드럽고 담백한 참치살을 사용한 대표적인 초밥입니다. 적당한 두께로 썰어내어 밥과 함께 즐기면 담백함과 고소함이 입안에 맴도는 정통 스시입니다.", 11500, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item33 = createItem("장어 초밥", "/image33.jpg", "장어 초밥 입니다.", "달콤한 간장 양념에 구운 부드러운 장어를 올린 초밥입니다. 입안에서 녹는 듯한 장어와 달짝지근한 소스가 조화를 이루어 깊은 풍미를 전합니다.", 13000, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item34 = createItem("새우 초밥", "/image34.jpg", "새우 초밥 입니다.", "탱글한 식감의 새우를 살짝 익혀 밥 위에 올린 새우 초밥입니다. 단맛이 도는 새우의 풍미와 쫀득한 밥이 어우러져 누구나 부담 없이 즐길 수 있습니다.", 10500, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item35 = createItem("계란 초밥", "/image35.jpg", "계란 초밥 입니다.", "달콤하게 조리된 일본식 계란말이를 밥 위에 얹은 계란 초밥입니다. 부드러운 계란의 식감과 달짝지근한 맛이 특징으로, 어린이나 초밥 입문자에게 인기가 많습니다.", 9500, 5, Category.JFOOD, SubCategory.SUSHI, ItemStatus.NEW);
      Item item36 = createItem("불닭발", "/image36.jpg", "불닭발입니다.", "매콤한 양념에 쫄깃한 닭발을 볶아낸 불닭발입니다. 불향과 고추기름의 얼얼한 매운맛이 어우러져 술안주로 제격입니다.", 11000, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item37 = createItem("마늘 간장 꼬치", "/image37.jpg", "마늘 간장 꼬치입니다.", "간장과 마늘로 양념된 닭꼬치를 직화로 구워낸 마늘 간장 꼬치입니다. 달짝지근하고 불향이 가득해 맥주와 잘 어울립니다.", 10000, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item38 = createItem("오돌뼈 볶음", "/image38.jpg", "오돌뼈 볶음입니다.", "쫄깃한 돼지 오돌뼈를 매콤한 양념으로 볶아낸 오돌뼈 볶음입니다. 감칠맛과 매운맛이 어우러져 중독성 있는 안주입니다.", 10500, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item39 = createItem("모둠튀김", "/image39.jpg", "모둠튀김입니다.", "새우, 오징어, 고구마 등 다양한 재료를 바삭하게 튀겨낸 모둠튀김입니다. 기름지지 않고 담백한 튀김으로 소주와 궁합이 뛰어납니다.", 9900, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item40 = createItem("치즈불닭", "/image40.jpg", "치즈불닭입니다.", "매콤한 불닭 위에 고소한 치즈를 듬뿍 올려 오븐에 구워낸 치즈불닭입니다. 맵고 고소한 맛의 조화가 일품입니다.", 11500, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item41 = createItem("제육볶음", "/image41.jpg", "제육볶음입니다.", "매콤한 고추장 양념에 돼지고기를 볶아낸 제육볶음입니다. 고기와 양파, 대파의 조화로 술안주와 밥반찬으로 모두 훌륭합니다.", 10900, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item42 = createItem("명란 계란말이", "/image42.jpg", "명란 계란말이입니다.", "짭짤한 명란을 부드러운 계란 속에 넣어 만든 명란 계란말이입니다. 은은한 감칠맛과 촉촉한 식감이 매력적입니다.", 9700, 5, Category.ANJU, SubCategory.REST, ItemStatus.NEW);
      Item item43 = createItem("순살 양념닭강정", "/image43.jpg", "순살 양념닭강정입니다.", "한입 크기의 순살 치킨에 달콤매콤한 양념을 입힌 닭강정입니다. 바삭함과 양념의 조화가 뛰어난 인기 안주입니다.", 10800, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item44 = createItem("골뱅이 무침", "/image44.jpg", "골뱅이 무침입니다.", "쫄깃한 골뱅이에 채 썬 야채와 매콤새콤한 양념을 더해 무쳐낸 골뱅이 무침입니다. 시원한 맥주와 잘 어울리는 대표 안주입니다.", 10200, 5, Category.ANJU, SubCategory.REST, ItemStatus.NEW);
      Item item45 = createItem("간장 마늘 치킨", "/image45.jpg", "간장 마늘 치킨입니다.", "달콤짭짤한 간장 소스에 마늘향을 더해 바삭하게 튀긴 닭에 버무린 간장 마늘 치킨입니다. 부드러운 육질과 풍부한 맛이 특징입니다.", 11900, 5, Category.ANJU, SubCategory.GRILL, ItemStatus.NEW);
      Item item46 = createItem("돈까스", "/image46.jpg", "돈까스 입니다.", "두툼하게 썰어낸 돼지고기를 바삭하게 튀겨낸 정통 돈까스입니다. 고소한 풍미와 진한 소스가 어우러져 남녀노소 모두에게 사랑받는 메뉴입니다.", 12000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item47 = createItem("고구마 치즈 돈까스", "/image47.jpg", "고구마 치즈 돈까스 입니다.", "달콤한 고구마와 부드러운 치즈를 돈까스 속에 가득 담았습니다. 겉은 바삭하고 속은 촉촉한, 달콤한 풍미의 색다른 돈까스를 즐겨보세요.", 14500, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item48 = createItem("생선까스", "/image48.jpg", "생선까스 입니다.", "담백한 흰살 생선을 바삭하게 튀겨낸 부드러운 생선까스입니다. 특제 타르타르 소스와 곁들이면 더욱 풍부한 맛을 느낄 수 있습니다.", 13000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item49 = createItem("새우 튀김", "/image49.jpg", "새우 튀김 입니다.", "큼직한 통새우를 바삭하게 튀겨내 탱글한 식감을 살렸습니다. 입안 가득 퍼지는 새우의 감칠맛이 일품인 인기 메뉴입니다.", 6000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item50 = createItem("게맛살 튀김", "/image50.jpg", "게맛살 튀김 입니다.", "쫄깃한 게맛살을 고온에서 바삭하게 튀겨낸 고소한 메뉴입니다. 은은한 단맛과 바삭한 식감이 조화롭게 어우러져 간식으로도 제격입니다.", 8000, 5, Category.ROAST, SubCategory.FRIED, ItemStatus.NEW);
      Item item51 = createItem("순대 볶음", "/image51.jpg", "순대 볶음 입니다.", "고소한 순대에 매콤한 양념을 더해 불맛을 살린 볶음 요리입니다. 쫀득한 식감과 중독성 있는 양념이 어우러져 밥 반찬으로도 딱입니다.", 18000, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item52 = createItem("고추잡채", "/image52.jpg", "고추잡채 입니다.", "아삭한 채소와 고기를 달큰하고 짭짤하게 볶아낸 중국식 잡채입니다. 부드러운 꽃빵에 싸 먹으면 더욱 특별한 맛을 느낄 수 있습니다.", 16000, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item53 = createItem("오징어 볶음", "/image53.jpg", "오징어 볶음 입니다.", "탱글한 오징어와 야채를 매콤하게 볶아낸 국민 반찬입니다. 불향 가득한 양념이 밥과 찰떡같이 어우러지는 매콤한 메뉴입니다.", 15000, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item54 = createItem("불고기", "/image54.jpg", "불고기 입니다.", "달콤한 간장 양념에 재운 소고기를 부드럽게 볶아냈습니다. 감칠맛 나는 육즙과 채소의 조화로 남녀노소 모두 좋아하는 메뉴입니다.", 8000, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item55 = createItem("감바스", "/image55.jpg", "감바스 입니다.", "올리브오일에 마늘과 새우를 넣어 향긋하게 볶아낸 스페인식 요리입니다. 바게트에 올려 먹으면 더욱 풍미 깊은 맛을 즐기실 수 있습니다.", 9000, 5, Category.ROAST, SubCategory.BOKKEUM, ItemStatus.NEW);
      Item item56 = createItem("순대국", "/image56.jpg", "순대국 입니다.", "진하게 고아낸 사골 육수에 순대와 머릿고기, 내장 등을 듬뿍 넣어 깊은 풍미를 자랑합니다. 구수하고 진한 국물이 속을 따뜻하게 감싸주며 든든한 한 끼를 완성합니다.", 9000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item57 = createItem("육개장", "/image57.jpg", "육개장 입니다.", "소고기와 숙주, 고사리, 대파 등을 고추기름에 볶아 얼큰하게 끓여낸 전통 국물 요리입니다. 얼큰하고 칼칼한 맛이 입맛을 돋워주며 추운 날씨에도 제격입니다.", 11000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item58 = createItem("선지해장국", "/image58.jpg", "선지해장국 입니다.", "고소하고 부드러운 선지와 우거지, 각종 채소를 얼큰한 양념에 푹 끓여낸 깊은 맛의 해장국입니다. 진한 국물과 푸짐한 건더기가 어우러져 속을 든든하게 달래주는 전통 보양식입니다.", 12000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item59 = createItem("미역국", "/image59.jpg", "미역국 입니다.", "부드러운 미역과 고소한 소고기를 참기름에 볶은 뒤 오랜 시간 정성껏 끓였습니다. 깔끔하고 담백한 국물 맛이 부담 없이 즐기기 좋아 매일 먹어도 질리지 않습니다.", 8000, 5, Category.SOUP, SubCategory.JJIGAE, ItemStatus.NEW);
      Item item60 = createItem("꼬치어묵탕", "/image60.jpg", "꼬치어묵탕 입니다.", "다양한 종류의 어묵을 꼬치에 끼워 시원한 다시마 육수에 정성껏 끓여낸 탕입니다. 은은한 국물 맛과 쫄깃한 어묵이 어우러져 간식이나 식사로도 좋습니다.", 8500, 5, Category.SOUP, SubCategory.TANG, ItemStatus.NEW);
      Item item61 = createItem("감자탕", "/image61.jpg", "감자탕 입니다.", "돼지 등뼈와 푹 익은 감자, 시래기 등을 매콤한 양념으로 푹 끓여낸 얼큰한 국물 요리입니다. 뼈에 붙은 살점의 쫀득함과 얼큰한 국물이 어우러져 중독적인 맛을 자랑합니다.", 11000, 5, Category.SOUP, SubCategory.TANG, ItemStatus.NEW);
      Item item62 = createItem("갈비탕", "/image62.jpg", "갈비탕 입니다.", "오랜 시간 푹 고아낸 맑은 국물에 큼직한 갈비와 대추, 당면을 넣어 진한 감칠맛을 냈습니다. 부드럽게 익은 갈비살이 입안에서 살살 풀어지며, 깊은 국물 맛이 일품입니다.", 12000, 5, Category.SOUP, SubCategory.TANG, ItemStatus.NEW);
      Item item63 = createItem("소머리곰탕", "/image63.jpg", "소머리곰탕 입니다.", "소머리 고기를 정성껏 삶아낸 고소하고 진한 육수에 다양한 부위를 풍성하게 담았습니다. 쫄깃한 식감과 깔끔한 국물이 어우러져 속을 든든하게 채워줍니다.", 10000, 5, Category.SOUP, SubCategory.TANG, ItemStatus.NEW);
      Item item64 = createItem("삼계탕", "/image64.jpg", "삼계탕 입니다.", "영계 안에 찹쌀, 인삼, 대추, 마늘 등을 넣고 오랜 시간 끓여낸 보양식으로 영양이 풍부합니다. 따뜻한 국물과 부드러운 닭고기가 기력을 회복하는 데 안성맞춤입니다.", 9000, 5, Category.SOUP, SubCategory.TANG, ItemStatus.NEW);
      Item item65 = createItem("설렁탕", "/image65.jpg", "설렁탕 입니다.", "진한 사골 육수를 오래 끓여 고소하고 부드러운 맛을 살린 전통적인 한국식 국밥입니다. 고기와 국수, 밥을 함께 넣어 먹으면 든든하고 속이 편안해지는 메뉴입니다.", 9000, 5, Category.SOUP, SubCategory.TANG, ItemStatus.NEW);
      Item item66 = createItem("까르보나라", "/image66.jpg", "까르보나라 입니다.", "고소한 치즈와 진한 크림, 베이컨의 풍미가 어우러진 클래식한 크림 파스타입니다. 부드럽고 진한 소스가 면에 잘 어울려 깊은 감칠맛을 선사합니다.", 9500, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item67 = createItem("알리오 올리오", "/image67.jpg", "알리오 올리오 입니다.", "신선한 마늘과 올리브오일, 매콤한 페퍼론치노가 어우러진 담백한 오일 파스타입니다. 재료는 단순하지만 깔끔한 맛과 향긋한 풍미가 일품입니다.", 11500, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item68 = createItem("봉골레", "/image68.jpg", "봉골레 입니다.", "탱글한 바지락과 화이트 와인을 활용해 시원하고 깊은 맛을 낸 해산물 파스타입니다. 조개 육수의 감칠맛이 면에 배어 고급스러운 풍미를 느낄 수 있습니다.", 14000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item69 = createItem("로제 파스타", "/image69.jpg", "로제 파스타 입니다.", "토마토소스와 크림소스를 조화롭게 섞어 부드럽고 새콤한 맛을 동시에 즐길 수 있습니다. 고소하면서도 살짝 매콤한 풍미가 입안을 감싸는 인기 메뉴입니다.", 11000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item70 = createItem("트러플 크림 파스타", "/image70.jpg", "트러플 크림 파스타 입니다.", "고소한 크림소스에 트러플 오일을 더해 풍부하고 고급스러운 향을 더한 파스타입니다. 은은한 트러플 향과 진한 소스가 부드럽게 어우러져 특별한 맛을 자아냅니다.", 13000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item71 = createItem("새우 크림 파스타", "/image71.jpg", "새우 크림 파스타 입니다.", "탱글한 새우와 부드러운 크림소스가 만나 고소하면서도 깊은 풍미를 자랑합니다. 바다의 신선함과 부드러운 식감이 조화를 이루는 해산물 파스타입니다.", 12500, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item72 = createItem("라자냐", "/image72.jpg", "라자냐 입니다.", "고기, 치즈, 토마토소스를 층층이 쌓아 오븐에 구워낸 진한 맛의 파스타입니다. 풍성한 식감과 구수한 치즈 향이 어우러져 든든한 한 끼로 제격입니다.", 11000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item73 = createItem("페스토 파스타", "/image73.jpg", "페스토 파스타 입니다.", "바질과 올리브오일, 견과류를 갈아 만든 향긋한 소스가 면을 감싸 신선한 맛을 선사합니다. 고소하면서도 허브의 상큼한 풍미가 가볍게 즐기기 좋은 메뉴입니다.", 12000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item74 = createItem("뇨끼", "/image74.jpg", "뇨끼 입니다.", "감자와 밀가루로 만든 말랑말랑한 반죽을 소스로 버무린 파스타로, 부드럽고 쫀득한 식감이 매력적입니다. 크림, 토마토, 버터 세이지 등 다양한 소스와 잘 어우러져 깊고 포근한 맛을 선사합니다.", 15000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);
      Item item75 = createItem("푸타네스카", "/image75.jpg", "푸타네스카 입니다.", "토마토소스를 베이스로 올리브, 앤초비, 케이퍼를 넣어 감칠맛과 짭조름함이 살아 있는 개성 강한 파스타입니다. 강렬한 풍미와 독특한 향신료 조합이 어우러져 입맛을 확 돋워줍니다.", 16000, 5, Category.PASTA, SubCategory.Noodle, ItemStatus.NEW);


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
      itemService.saveItem(item36);
      itemService.saveItem(item37);
      itemService.saveItem(item38);
      itemService.saveItem(item39);
      itemService.saveItem(item40);
      itemService.saveItem(item41);
      itemService.saveItem(item42);
      itemService.saveItem(item43);
      itemService.saveItem(item44);
      itemService.saveItem(item45);
      itemService.saveItem(item46);
      itemService.saveItem(item47);
      itemService.saveItem(item48);
      itemService.saveItem(item49);
      itemService.saveItem(item50);
      itemService.saveItem(item51);
      itemService.saveItem(item52);
      itemService.saveItem(item53);
      itemService.saveItem(item54);
      itemService.saveItem(item55);
      itemService.saveItem(item56);
      itemService.saveItem(item57);
      itemService.saveItem(item58);
      itemService.saveItem(item59);
      itemService.saveItem(item60);
      itemService.saveItem(item61);
      itemService.saveItem(item62);
      itemService.saveItem(item63);
      itemService.saveItem(item64);
      itemService.saveItem(item65);
      itemService.saveItem(item66);
      itemService.saveItem(item67);
      itemService.saveItem(item68);
      itemService.saveItem(item69);
      itemService.saveItem(item70);
      itemService.saveItem(item71);
      itemService.saveItem(item72);
      itemService.saveItem(item73);
      itemService.saveItem(item74);
      itemService.saveItem(item75);

    memberService.saveAdminMember(AdminMember);
    }



  private Item createItem(String name, String image, String content,String subcontent, int price, int stock, Category category, SubCategory subCategory, ItemStatus itemStatus) {
    Item item=new Item();
    item.setName(name);
    item.setImage(image);
    item.setContent(content);
    item.setSubcontent(subcontent);
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
