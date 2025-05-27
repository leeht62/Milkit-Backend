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
  private final MemberService memberService;

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

  public BoardDto createBoard(BoardDto boardDto, String userCode) {
    Member member = memberRepository.findByUserCode(userCode);
    Board board = new Board(boardDto);
    board.addMember(member);
    boardRepository.save(board);
    BoardDto boardDtos = new BoardDto(board);
    return boardDtos;
  }

  @Transactional
  public void updateBoard(Long boardId, BoardDto boardDto, String userCode) {
    Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    Member member = memberService.findMemberByUserCode(userCode);
    if (board.getMember().getUserCode().equals(userCode) || member.getRole().name().equals("ROLE_ADMIN")){
      board.update(boardDto);
    }
  }

  @Transactional
  public void deleteBoard(Long id,String userCode) {
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    Member member=memberService.findMemberByUserCode(userCode);
    boolean isAdmin = member.getRole().name().equals("ROLE_ADMIN");
    boolean isOwner = board.getMember() != null && board.getMember().getUserCode().equals(userCode);

    if (isAdmin || isOwner) {
      boardRepository.deleteById(id);
    }
  }

  public BoardReadDto ReadBoard(Long boardId) {
    Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
    BoardReadDto boardReadDto = new BoardReadDto(board);
    return boardReadDto;
  }

  public boolean duplicateBoard(Long id, String userCode){
    Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return board.getMember().getUserCode().equals(userCode);
  }
}
