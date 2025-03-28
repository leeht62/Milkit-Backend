package com.milkit_shop.controller;


import com.milkit_shop.dto.BoardDto;
import com.milkit_shop.dto.CommentDto;
import com.milkit_shop.entity.Comment;
import com.milkit_shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;


  @GetMapping("/{boardId}/comment")
  public ResponseEntity<List<CommentDto>> getComment(@PathVariable Long boardId){
    return ResponseEntity.ok(commentService.getComment(boardId));
  }
  @PostMapping("/admin/{boardId}/comments")
  public ResponseEntity<CommentDto> postComment(@PathVariable Long boardId, Principal principal, @RequestBody CommentDto commentDto){
    String email = principal.getName();
    CommentDto commentDtos = commentService.createComment(boardId,email, commentDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(commentDtos);
  }
  @PatchMapping("/admin/{boardId}/deletecomment")
  public ResponseEntity<Void> deleteComment(@PathVariable Long boardId,@PathVariable Long commentId,Principal principal) {
    String email = principal.getName();
    commentService.deleteComment(boardId,email,commentId);
    return ResponseEntity.ok(null);
  }


}
