package com.milkit_shop.dto;

import com.milkit_shop.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReadDto {
  private String title;
  private String content;
  private String userCode;

  public BoardReadDto(Board board) {
    this.title = board.getTitle();
    this.content = board.getContent();
    this.userCode = board.getMember() != null ? board.getMember().getUserCode() : "탈퇴한 사용자";
  }
}
