package com.milkit_shop.controller;

import com.milkit_shop.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

  @Value("classpath:/item.sql")
  private Resource ddlResource;

  @Value("classpath:/sql-question.st")
  private Resource sqlquestion;

  private final ChatClient aiClient;
  private final JdbcTemplate jdbcTemplate;


  public AiController(ChatClient.Builder aiClient, JdbcTemplate jdbcTemplate) {
    this.aiClient = aiClient.build();
    this.jdbcTemplate = jdbcTemplate;
  }


  @PostMapping
  public AiService sql(@RequestParam(name = "question") String question) throws IOException {
    String data = ddlResource.getContentAsString(Charset.defaultCharset());
    String response = aiClient.prompt()
        .user(userSpec -> userSpec
            .text(sqlquestion)
            .param("question", question)
            .param("ddl", data)
        )
        .call()
        .content();

    String query = response.split("\n")[0].trim();

    if (query.toLowerCase().startsWith("select")) {
      List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
      return new AiService(query, results);
    }
    return new AiService(query, List.of());
  }
}
