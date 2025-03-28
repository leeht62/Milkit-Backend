package com.milkit_shop.repository;

import com.milkit_shop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{
  List<Comment> findByBoardId(Long boardId);
}
