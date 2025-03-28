package com.milkit_shop.controller;

import com.milkit_shop.dto.BoardDto;
import com.milkit_shop.dto.BoardReadDto;
import com.milkit_shop.entity.Board;
import com.milkit_shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;

  @GetMapping("/mainboard")
  public ResponseEntity<List<BoardDto>> getBoard(@RequestParam(value = "search", required = false) String search){
    if (search == null){
      return ResponseEntity.ok(boardService.getBoard());
    }else{
      return ResponseEntity.ok(boardService.SearchBoard(search));
    }
  }
  @GetMapping("/{boardId}/read")
  public ResponseEntity<BoardReadDto> ReadBoard(@PathVariable Long boardId){
    return ResponseEntity.ok(boardService.ReadBoard(boardId));
  }
  @PostMapping("/write")
  public ResponseEntity<BoardDto> create(@RequestBody BoardDto dto, Principal principal) {
    String email = principal.getName();
    BoardDto boardDto=boardService.createBoard(dto,email);
    return ResponseEntity.status(HttpStatus.CREATED).body(boardDto);
  }
  @PatchMapping("/{boardId}/delete")
  public ResponseEntity<Void> delete(@PathVariable Long boardId,Principal principal) {
    String email = principal.getName();
    boardService.deleteBoard(boardId,email);
    return ResponseEntity.ok(null);
  }
  @PutMapping("/{boardId}/modify")
  public ResponseEntity<Void> update(@PathVariable Long boardId,@RequestBody BoardDto dto,Principal principal) {
    String email = principal.getName();
    boardService.updateBoard(boardId,dto,email);
    return ResponseEntity.ok(null);
  }


}
