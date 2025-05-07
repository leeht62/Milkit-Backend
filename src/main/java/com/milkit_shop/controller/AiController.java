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
import java.util.*;
import java.util.stream.Collectors;

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
    List<Item> items = itemRepository.findAll();

    StringBuilder foodListText = new StringBuilder();
    for (Item item : items) {
      foodListText.append("- ").append(item.getName()).append(": ").append(item.getContent()).append("\n");
    }

    String prompt = food + "와 관련된 식품 3가지를 추천해주세요. 목록에 없는 음식은 절대 포함하지 마세요.\n" +
        "아래는 다양한 음식 목록입니다:\n" +
        foodListText.toString() +
        "위 목록에서 "+food+"와 잘 어울리는 음식 3가지를 각각 하나씩 독립적으로 추천해 주세요. 각 추천 항목은 다음과 같은 형식으로 작성해 주세요: 1. 음식이름: 설명(※ 음식이름은 반드시 하나의 음식만 작성해 주세요. 두 개 이상 조합하지 마세요.)";

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

    List<Map<String, Object>> recommendedFoods = new ArrayList<>();
    Set<String> validItemNames = items.stream()
        .map(Item::getName)
        .collect(Collectors.toSet());

    for (String line : responseLines) {
      if (line.contains(":")) {
        String[] foodInfo = line.split(":");
        if (foodInfo.length == 2) {
          String name = foodInfo[0].trim().replaceAll("^[0-9]+\\.\\s*", "");
          String description = foodInfo[1].trim();

          if (validItemNames.contains(name)) {
            Map<String, Object> foodMap = new HashMap<>();
            foodMap.put("name", name);
            foodMap.put("description", description);
            recommendedFoods.add(foodMap);
          }
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



