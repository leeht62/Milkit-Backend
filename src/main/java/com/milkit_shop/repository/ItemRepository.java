package com.milkit_shop.repository;

import com.milkit_shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
  List<Item> findByName(String name);
}
