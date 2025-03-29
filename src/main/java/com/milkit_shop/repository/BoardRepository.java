package com.milkit_shop.repository;

import com.milkit_shop.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
  @Query("SELECT b FROM Board b " +
      "WHERE b.title LIKE %:search% " +
      "OR b.content LIKE %:search% " +
      "ORDER BY b.Date DESC")
  List<Board> findBoardsBySearch(@Param("search") String search);

  @Query("select b from Board b " +
      "order by b.Date desc")
  List<Board> findAllBoards();
}
