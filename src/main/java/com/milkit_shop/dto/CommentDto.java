  package com.milkit_shop.dto;

  import com.milkit_shop.entity.Comment;
  import com.milkit_shop.entity.Member;
  import lombok.Getter;
  import lombok.NoArgsConstructor;
  import lombok.Setter;


  @Getter
  @Setter
  @NoArgsConstructor
  public class CommentDto {
    private Long id;
    private String comment;
    private Member member;
    public CommentDto(Comment comment) {
      this.id = comment.getId();
      this.comment = comment.getComment();
      this.member=comment.getMember();
    }
  }
