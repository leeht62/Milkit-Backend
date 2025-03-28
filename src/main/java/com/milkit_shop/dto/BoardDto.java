package com.milkit_shop.dto;

import com.milkit_shop.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
  private Long id;
  private String title;
  private String content;

  public BoardDto(Board board) {
    this.id = board.getId();
    this.title = board.getTitle();
    this.content = board.getContent();
  }
}
