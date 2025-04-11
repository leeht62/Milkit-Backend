package com.milkit_shop.entity;

import com.milkit_shop.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  @Id
  @GeneratedValue
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;

  @ManyToOne(fetch = FetchType.EAGER)
  private Member member;

  @Column(nullable = false)
  private String comment;

  public Comment(CommentDto commentDto) {
    this.comment = commentDto.getComment();
  }

  public void addMember(Member member) {
    this.member = member;
    member.getComments().add(this);
  }

  public void addBoard(Board board) {
    this.board = board;
    board.getComments().add(this);
  }

  public void change(CommentDto commentDto) {
    this.comment = commentDto.getComment();
  }

}
