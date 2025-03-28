package com.milkit_shop.dto;

import com.milkit_shop.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReadDto {
  private String title;
  private String content;
  private String memberEmail;

  public BoardReadDto(Board board) {
    this.title = board.getTitle();
    this.content = board.getContent();
    this.memberEmail = board.getMember().getEmail();
  }
}
