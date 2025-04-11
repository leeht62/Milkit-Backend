package com.milkit_shop.controller;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;


  @GetMapping("/list")
  public ResponseEntity<List<ItemDto>> getAllItems(@RequestParam(value = "search", required = false) String search) {
    List<ItemDto> itemList = itemService.itemList();
    if (search == null){
      return ResponseEntity.ok(itemList);
    }else{
      return ResponseEntity.ok(itemService.itemListSearch(search));
    }
  }


}
