package com.milkit_shop.controller;


import com.milkit_shop.dto.CommentDto;
import com.milkit_shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<CommentDto> createComment(@PathVariable Long boardId, Principal principal, @RequestBody CommentDto commentDto){
    String email = principal.getName();
    CommentDto commentDtos = commentService.createComment(boardId,email, commentDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(commentDtos);
  }
  @PatchMapping("/admin/{commentId}/deletecomment")
  public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,Principal principal) {
    String email = principal.getName();
    commentService.deleteComment(email,commentId);
    return ResponseEntity.ok(null);
  }


}
