package com.milkit_shop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.milkit_shop.dto.BoardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
  @Id
  @GeneratedValue
  private Long id;


  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "member_id", nullable = true)
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @JsonIgnore
  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  @Column
  private LocalDateTime Date;

  public Board(BoardDto boardDto) {
    this.title = boardDto.getTitle();
    this.content = boardDto.getContent();
    this.Date =LocalDateTime.now();
  }
  public void addMember(Member member) {
    this.member = member;
    member.getBoards().add(this);
  }
  public void update(BoardDto boardDto) {
    this.title = boardDto.getTitle();
    this.content = boardDto.getContent();
  }

}
