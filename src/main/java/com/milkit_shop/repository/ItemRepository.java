package com.milkit_shop.repository;

import com.milkit_shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
  List<Item> findByName(String name);

  @Query("SELECT i FROM Item i " +
      "WHERE i.name LIKE %:search% " +
      "ORDER BY i.id DESC")
  List<Item> findItemsBySearch(@Param("search") String search);
}
