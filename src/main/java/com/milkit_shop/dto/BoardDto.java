package com.milkit_shop.dto;

import com.milkit_shop.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
  private Long id;
  private LocalDateTime date;
  private String title;
  private String content;
  private String userCode;
  private String name;

  public BoardDto(Board board) {
    this.id = board.getId();
    this.title = board.getTitle();
    this.content = board.getContent();
    this.date = board.getDate();
    this.userCode = board.getMember() != null ? board.getMember().getUserCode() : null;
    this.name =  board.getMember() != null ? board.getMember().getName() : "탈퇴한 사용자";
  }
}
