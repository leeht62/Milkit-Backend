package com.milkit_shop.controller;

import com.milkit_shop.entity.Item;
import com.milkit_shop.repository.ItemRepository;
import com.milkit_shop.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiController {

  @Value("classpath:/item.sql")
  private Resource finddatabase;

  private final ChatClient aiClient;
  private final ItemRepository itemRepository;

  public AiController(ChatClient.Builder aiClient,ItemRepository itemRepository) {
    this.aiClient = aiClient.build();
    this.itemRepository = itemRepository;
  }

  @PostMapping("/chat")
  public AiService getFoodPairings(@RequestParam(name = "food") String food) throws IOException {

    Map<String, Object> params = new HashMap<>();
    params.put("chat", food);
    params.put("database", finddatabase.getContentAsString(Charset.defaultCharset()));
    // GPT 프롬프트 생성
    List<Item> items = itemRepository.findAll(); // 예시: 모든 상품 조회

    // 2. 상품 목록을 GPT가 이해할 수 있는 형식으로 변환
    StringBuilder foodListText = new StringBuilder();
    for (Item item : items) {
      foodListText.append("- ").append(item.getName()).append(": ").append(item.getContent()).append("\n");
    }

    // 3. '김치'와 어울리는 제품을 추천하도록 GPT에게 전달할 프롬프트 작성
    String prompt = food + "와 관련된 식품 3가지를 추천해주세요. 아래는 다양한 음식 목록입니다:\n" +
        foodListText.toString() +
        "위 목록에서"+food+"와 잘 어울리는 3개의 음식을 추천해 주세요. ";

    log.info("상품목록:{}", foodListText.toString());
    // GPT 호출
    String response = aiClient
        .prompt()
        .user(user -> {
          user.text(prompt);
          user.params(params);
        })
        .call()
        .content();

    String[] responseLines = response.split("\n");

    // 응답에서 음식 리스트를 추출하거나 필터링
    List<Map<String, Object>> recommendedFoods = new ArrayList<>();
    for (String line : responseLines) {
      if (line.contains(":")) {  // 각 음식이 ":"를 포함하는 라인인 경우 음식 목록으로 간주
        String[] foodInfo = line.split(":");
        if (foodInfo.length == 2) {
          String name = foodInfo[0].trim().replaceAll("^[0-9]+\\.\\s*", "");
          String description = foodInfo[1].trim();

          // Map을 생성하여 이름과 설명을 담기
          Map<String, Object> foodMap = new HashMap<>();
          foodMap.put("name", name);
          foodMap.put("description", description);

          recommendedFoods.add(foodMap);
        }
      }
    }

    // 결과를 반환
    if (recommendedFoods.isEmpty()) {
      return new AiService("추천된 음식이 없습니다.", List.of());
    } else {
      return new AiService("추천 음식 목록", recommendedFoods);
    }
  }
}



