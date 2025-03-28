package com.milkit_shop.service;

import com.milkit_shop.dto.BoardDto;
import com.milkit_shop.dto.BoardReadDto;
import com.milkit_shop.entity.Board;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.BoardRepository;
import com.milkit_shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;
  public List<BoardDto> getBoard() {
    List<Board> boards = boardRepository.findAllBoards();
    List<BoardDto> BoardDtoList = new ArrayList<>();
    for(Board board : boards){
      BoardDto mainDto = new BoardDto(board);
      BoardDtoList.add(mainDto);
    }
    return BoardDtoList;
  }

  public List<BoardDto> SearchBoard(String search) {
    List<Board> boards = boardRepository.findBoardsBySearch(search);
    List<BoardDto> BoardDtoList = new ArrayList<>();
    for(Board board : boards){
      BoardDto mainDto = new BoardDto(board);
      BoardDtoList.add(mainDto);
    }
    return BoardDtoList;
  }

  public BoardDto createBoard(BoardDto boardDto, String email) {
    Member member = memberRepository.findByEmail(email);
    Board board = new Board(boardDto);
    board.addMember(member);
    boardRepository.save(board);
    BoardDto boardDtos = new BoardDto(board);
    return boardDtos;
  }
  @Transactional
  public void updateBoard(Long boardId, BoardDto boardDto, String email) {
    Member member = memberRepository.findByEmail(email);
    Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    if (board.getMember().getEmail().equals(email)){
      board.update(boardDto);
    }
  }
  @Transactional
  public void deleteBoard(Long id,String email) {
    Member member = memberRepository.findByEmail(email);
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    if (board.getMember().getEmail().equals(email)){
      boardRepository.deleteById(id);
    }
  }
  public BoardReadDto ReadBoard(Long boardId) { // 게시글 작성한 사람의 아이디와 이메일을 보낸다.
    Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    BoardReadDto boardReadDto = new BoardReadDto(board);
    return boardReadDto;
  }

}
