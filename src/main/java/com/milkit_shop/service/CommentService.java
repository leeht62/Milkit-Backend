package com.milkit_shop.service;

import com.milkit_shop.dto.CommentDto;
import com.milkit_shop.entity.Board;
import com.milkit_shop.entity.Comment;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.BoardRepository;
import com.milkit_shop.repository.CommentRepository;
import com.milkit_shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final MemberRepository memberRepository;
  private final BoardRepository boardRepository;

  @Transactional
  public List<CommentDto> getComment(Long boardId) {
    List<Comment> comments = commentRepository.findByBoardId(boardId);
    List<CommentDto> commentDtoList = new ArrayList<>();
    for(Comment comment : comments){
      CommentDto commentDto = new CommentDto(comment);
      commentDtoList.add(commentDto);
    }
    return commentDtoList;

  }

  public CommentDto createComment(Long boardId,String email, CommentDto commentDto) {
    Member member = memberRepository.findByEmail(email);
    Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    Comment comment = new Comment(commentDto);
    comment.addMember(member);
    comment.addBoard(board);
    commentRepository.save(comment);
    CommentDto commentDtos = new CommentDto(comment);
    return commentDtos;
  }

  @Transactional
  public void deleteComment(Long BoardId,String email,Long commentId) {
    Member member = memberRepository.findByEmail(email);
    Board board = boardRepository.findById(BoardId).orElseThrow(EntityNotFoundException::new);
    Comment comment=commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);
    if (board.getMember().getEmail().equals(email)){
      commentRepository.delete(comment);
    }
  }

}
